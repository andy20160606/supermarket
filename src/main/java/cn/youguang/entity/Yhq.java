package cn.youguang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "t_yhq")
public class Yhq extends IdEntity {

    private Double je; //优惠券金额


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ksrq; //开始日期

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date jsrq; //结束日期

    private String yhm;


    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    @ManyToOne
    @JsonIgnore
    private User user;


    @ManyToOne
    private Yhhd yhhd;

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public Date getKsrq() {
        return ksrq;
    }

    public void setKsrq(Date ksrq) {
        this.ksrq = ksrq;
    }

    public Date getJsrq() {
        return jsrq;
    }

    public void setJsrq(Date jsrq) {
        this.jsrq = jsrq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Yhhd getYhhd() {
        return yhhd;
    }

    public void setYhhd(Yhhd yhhd) {
        this.yhhd = yhhd;
    }
}
