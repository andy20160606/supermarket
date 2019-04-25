package cn.youguang.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 *
 * 产品
 */
@Entity
@Table(name="t_sjrz")
public class Sjrz extends IdEntity {


    private Date sjrq; //数据日期

    @ManyToOne
    private Cp cp;

    @ManyToOne
    private Yhhd yhhd;

    private String sjxw; //数据行为 ： zf or ll

    private Long count; //次数


    public Date getSjrq() {
        return sjrq;
    }

    public void setSjrq(Date sjrq) {
        this.sjrq = sjrq;
    }

    public Cp getCp() {
        return cp;
    }

    public void setCp(Cp cp) {
        this.cp = cp;
    }

    public Yhhd getYhhd() {
        return yhhd;
    }

    public void setYhhd(Yhhd yhhd) {
        this.yhhd = yhhd;
    }

    public String getSjxw() {
        return sjxw;
    }

    public void setSjxw(String sjxw) {
        this.sjxw = sjxw;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
