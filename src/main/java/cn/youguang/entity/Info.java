package cn.youguang.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * key_val 表 val 是多值
 */

@Entity
@Table(name = "t_info")
public class Info extends IdEntity {


    @Column(name = "k")
    private String key;

    private String val;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }


    @Override
    public String toString() {
        return "Info{" +
                "key='" + key + '\'' +
                ", val='" + val + '\'' +
                ", id=" + id +
                '}';
    }
}
