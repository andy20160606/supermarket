package cn.youguang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;



@Entity
@Table(name ="t_yhhd")
public class Yhhd extends IdEntity {


    private String hdmc;  // 活动名称

    private Double je; //优惠金额

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ksrq; //活动开始日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date jsrq; //活动结束日期


    private String hdsm; //活动说明

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
    }

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

    public String getHdsm() {
        return hdsm;
    }

    public void setHdsm(String hdsm) {
        this.hdsm = hdsm;
    }

    @Override
    public String toString() {
        return "Yhhd{" +
                "hdmc='" + hdmc + '\'' +
                ", je=" + je +
                ", ksrq=" + ksrq +
                ", jsrq=" + jsrq +
                ", hdsm='" + hdsm + '\'' +
                ", id=" + id +
                '}';
    }
}
