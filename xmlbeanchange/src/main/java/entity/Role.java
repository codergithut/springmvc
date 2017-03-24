package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/24
 * @description
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name", "desc" })
public class Role {

    @XmlElement
    private String name;

    @XmlElement
    private String desc;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [name=" + name + ", desc=" + desc + "]";
    }

}