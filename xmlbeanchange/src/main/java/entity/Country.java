package entity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/24
 * @description
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "country")
@XmlType(propOrder = { "name", "provinceList" })
public class Country {

    @XmlElement(name = "country_name")
    private String name;

    @XmlElementWrapper(name = "provinces")
    @XmlElement(name = "province")
    private List<Province> provinceList;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the provinceList
     */
    public List<Province> getProvinceList() {
        return provinceList;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param provinceList the provinceList to set
     */
    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Country [name=" + name + ", provinceList=" + provinceList + "]";
    }

}
