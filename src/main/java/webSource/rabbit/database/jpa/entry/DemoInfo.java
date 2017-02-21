package webSource.rabbit.database.jpa.entry;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/20
 * @description
 */

@Entity
public class DemoInfo {
    @Id@GeneratedValue
    private long id;
    private String name;
    private String path;



    public DemoInfo() {
    }

    public DemoInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getId() {
        return id;
    }
}