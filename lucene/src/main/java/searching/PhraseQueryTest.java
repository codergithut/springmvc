package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class PhraseQueryTest extends TestCase {
    private Directory dir;
    private IndexSearcher searcher;
    private Analyzer analyzer;

    protected void setUp() throws IOException {
        dir = TestUtil.getBookIndexDirectory();
        IndexWriter writer = new IndexWriter(dir, new WhitespaceAnalyzer(),
                IndexWriter.MaxFieldLength.UNLIMITED);
        Document doc = new Document();
        doc.add(new Field("field", "the quick brown fox jumped over the lazy dog",
                Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);
        writer.close();
        searcher = new IndexSearcher(dir);
        analyzer = new WhitespaceAnalyzer();
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

    public void testFuzzy() throws IOException {
        indexSingleFieldDocs( new Field[]{
                        new Field("contents", "fuzzy", Field.Store.YES, Field.Index.ANALYZED ),
                        new Field("contents", "wuzzy", Field.Store.YES, Field.Index.ANALYZED)
        });
        IndexSearcher searcher = new IndexSearcher(dir);
        Query query = new FuzzyQuery(new Term("contents", "wuzza"));
        TopDocs matches = searcher.search(query, 10);
        assertEquals("both close enought", 2, matches.totalHits);
        assertTrue("wuzzy close than fuzzy", matches.scoreDocs[0].score > matches.scoreDocs[1].score);
        searcher.close();

    }

    public void testToString(){
        BooleanQuery query = new BooleanQuery();
        query.add(new FuzzyQuery(new Term("field", "kountry")),BooleanClause.Occur.MUST);
        query.add(new TermQuery(new Term("title", "western")), BooleanClause.Occur.SHOULD);
        assertEquals("both kinds", "+kountry~0.5 title:western", query.toString("field"));
    }

    public void testTermQuery() throws ParseException {
        QueryParser parser = new QueryParser(Version.LUCENE_30,"subject", new SimpleAnalyzer());
        Query query = parser.parse("computers");
        System.out.println("term: " + query);
    }

    public void testTermRangeQuery() throws ParseException, IOException {
        Query query = new QueryParser(Version.LUCENE_30, "subject", new SimpleAnalyzer())
                .parse("title2:[Q TO V]");
        assertTrue(query instanceof TermRangeQuery);
        TopDocs matches = searcher.search(query, 10);
        assertTrue(TestUtil.hitsIncludeTitle(searcher, matches, "Tapestry in Action"));
        query = new QueryParser(Version.LUCENE_30,"subject", analyzer)
                .parse("title2:{Q TO \"Tapestry in Action\" }");
        matches = searcher.search(query, 10);

        assertFalse(TestUtil.hitsIncludeTitle(searcher, matches, "Tapestry in Action"));


    }

}
