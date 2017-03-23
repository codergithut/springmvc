package searching;

import junit.framework.TestCase;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class PhraseQueryTest extends TestCase {
    private Directory dir;
    private IndexSearcher searcher;
    protected void setUp() throws IOException {
        dir = new RAMDirectory();
        IndexWriter writer = new IndexWriter(dir, new WhitespaceAnalyzer(),
                IndexWriter.MaxFieldLength.UNLIMITED);
        Document doc = new Document();
        doc.add(new Field("field", "the quick brown fox jumped over the lazy dog",
                Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);
        writer.close();
        searcher = new IndexSearcher(dir);
    }

    protected void tearDown() throws IOException {
        searcher.close();
        dir.close();
    }

    private boolean matched(String[] phrase, int slop) throws IOException {
        PhraseQuery query = new PhraseQuery();
        query.setSlop(slop);
        for(String word : phrase){
            query.add(new Term("field", word));
        }
        TopDocs matches = searcher.search(query, 10);
        return matches.totalHits > 0;
    }

    public void testSlopComparison() throws IOException {
        String[] phrase = new String[]{"quick", "fox"};

        assertFalse("exact phrase not found", matched(phrase, 0));

        assertTrue("close enough", matched(phrase, 1));
    }

    public void testReverse() throws IOException {
        String[] pharse = new String[]{"fox", "quick"};

        assertFalse("hot flop", matched(pharse,2));

        assertTrue("hop hop slop", matched(pharse,3));
    }

    public void testMultiple() throws IOException {
        assertFalse("not close enough", matched(new String[] {"quick", "jumped", "lazy"}, 3));

        assertTrue("just enough", matched(new String[] {"quick", "jumped", "lazy"}, 4));

        assertFalse("almost but not quite", matched(new String[] {"lazy", "jumped", "quick"}, 7));

        assertTrue("bingo", matched(new String[] {"lazy", "jumped", "quick"}, 8));
    }

    private void indexSingleFieldDocs(Field[] fields) throws IOException {
        IndexWriter writer = new IndexWriter(dir, new WhitespaceAnalyzer(),
                IndexWriter.MaxFieldLength.UNLIMITED);
        for(Field f : fields){
            Document doc = new Document();
            doc.add(f);
            writer.addDocument(doc);
        }
        writer.optimize();
        writer.close();
    }

    public void testWildcard() throws IOException {
        indexSingleFieldDocs(new Field[]
                {
                        new Field("contents", "wild", Field.Store.YES, Field.Index.ANALYZED ),
                        new Field("contents", "child", Field.Store.YES, Field.Index.ANALYZED),
                        new Field("contents", "mild", Field.Store.YES, Field.Index.ANALYZED),
                        new Field("contents", "mildew", Field.Store.YES, Field.Index.ANALYZED)
                });

        IndexSearcher searcher = new IndexSearcher(dir);
        Query query = new WildcardQuery(new Term("contents", "?ild*"));
        TopDocs matches = searcher.search(query, 10);
        assertEquals("child no match", 3, matches.totalHits);
        assertEquals("score the same", matches.scoreDocs[0].score, matches.scoreDocs[1].score, 0.0);
        assertEquals("score the same", matches.scoreDocs[1].score, matches.scoreDocs[2].score, 0.0);
        searcher.close();
    }



}
