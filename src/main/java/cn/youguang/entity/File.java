package cn.youguang.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  文件类
 */

@Entity
@Table(name = "t_file")
public class File extends IdEntity{

    private String attr1;

    private String attr2;

    private String attr3;

    private String url; //文件地址


    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
