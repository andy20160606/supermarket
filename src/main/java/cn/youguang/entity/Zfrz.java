package cn.youguang.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 产品
 */
@Entity
@Table(name = "t_zfrz")
public class Zfrz extends IdEntity {


    private Date zfsj; //转发时间

    @ManyToOne
    private User zfr;  //转发人

    @ManyToOne
    private Cp cp;

    @ManyToOne
    private Yhhd yhhd;


    private String url;  //转发地址

    private String wybs; //uuid做唯一标识

    private Long llcs;//浏览次数


    public Date getZfsj() {
        return zfsj;
    }

    public void setZfsj(Date zfsj) {
        this.zfsj = zfsj;
    }

    public User getZfr() {
        return zfr;
    }

    public void setZfr(User zfr) {
        this.zfr = zfr;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getWybs() {
        return wybs;
    }

    public void setWybs(String wybs) {
        this.wybs = wybs;
    }

    public Long getLlcs() {
        return llcs;
    }

    public void setLlcs(Long llcs) {
        this.llcs = llcs;
    }
}
