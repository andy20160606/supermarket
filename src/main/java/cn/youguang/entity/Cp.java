package cn.youguang.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 *
 * 产品
 */
@Entity
@Table(name="t_cp")
public class Cp extends IdEntity {


    private String cpmc;

    private String cpxx;//产品信息

    private String cplb;//产品类别

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sjsj;  //上架时间

    private Integer cpzt; //产品状态 0 下架 1 上架

    private Double cpyj; //产品原价

    private Double cpxj;  //产品现价


    private String cpzst; //产品展示图 对应文件名


    private String cpxqt1; //产品详情图1 对应文件名

    private String cpxqt2; //产品详情图2 对应文件名




    @JsonIgnore
    @ManyToMany(fetch= FetchType.LAZY,mappedBy="cps")
    private List<User> users;



    @ManyToMany
    private List<Hy> hys;


    @ManyToMany
    private List<Zt> zts;


    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getCpxx() {
        return cpxx;
    }

    public void setCpxx(String cpxx) {
        this.cpxx = cpxx;
    }

    public String getCplb() {
        return cplb;
    }

    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public Date getSjsj() {
        return sjsj;
    }

    public void setSjsj(Date sjsj) {
        this.sjsj = sjsj;
    }

    public Integer getCpzt() {
        return cpzt;
    }

    public void setCpzt(Integer cpzt) {
        this.cpzt = cpzt;
    }

    public Double getCpyj() {
        return cpyj;
    }

    public void setCpyj(Double cpyj) {
        this.cpyj = cpyj;
    }

    public Double getCpxj() {
        return cpxj;
    }

    public void setCpxj(Double cpxj) {
        this.cpxj = cpxj;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Hy> getHys() {
        return hys;
    }

    public void setHys(List<Hy> hys) {
        this.hys = hys;
    }

    public String getCpzst() {
        return cpzst;
    }

    public void setCpzst(String cpzst) {
        this.cpzst = cpzst;
    }

    public String getCpxqt1() {
        return cpxqt1;
    }

    public void setCpxqt1(String cpxqt1) {
        this.cpxqt1 = cpxqt1;
    }

    public String getCpxqt2() {
        return cpxqt2;
    }

    public void setCpxqt2(String cpxqt2) {
        this.cpxqt2 = cpxqt2;
    }


    public List<Zt> getZts() {
        return zts;
    }

    public void setZts(List<Zt> zts) {
        this.zts = zts;
    }
}
