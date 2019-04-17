package cn.youguang.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


/**
 *
 * 产品订单
 */
@Entity
@Table(name="t_cpdd")
public class Cpdd extends IdEntity {



    private String khxm; //客户姓名

    private String khsj;// 客户手机

    private String khdz; //客户地址

    private String qtxx; //其他信息

    private String ddlx;  //订单类型  产品 转发 会员 定制开发等

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date xdsj; //下单时间

    private Integer ddzt; //订单状态

    @ManyToOne
    private Cp cp;


    @ManyToOne
    @JsonIgnore
    private User user;


    public String getKhxm() {
        return khxm;
    }

    public void setKhxm(String khxm) {
        this.khxm = khxm;
    }

    public String getKhsj() {
        return khsj;
    }

    public void setKhsj(String khsj) {
        this.khsj = khsj;
    }

    public String getKhdz() {
        return khdz;
    }

    public void setKhdz(String khdz) {
        this.khdz = khdz;
    }

    public String getQtxx() {
        return qtxx;
    }

    public void setQtxx(String qtxx) {
        this.qtxx = qtxx;
    }

    public String getDdlx() {
        return ddlx;
    }

    public void setDdlx(String ddlx) {
        this.ddlx = ddlx;
    }

    public Date getXdsj() {
        return xdsj;
    }

    public void setXdsj(Date xdsj) {
        this.xdsj = xdsj;
    }

    public Integer getDdzt() {
        return ddzt;
    }

    public void setDdzt(Integer ddzt) {
        this.ddzt = ddzt;
    }

    public Cp getCp() {
        return cp;
    }

    public void setCp(Cp cp) {
        this.cp = cp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Cpdd{" +
                "khxm='" + khxm + '\'' +
                ", khsj='" + khsj + '\'' +
                ", khdz='" + khdz + '\'' +
                ", qtxx='" + qtxx + '\'' +
                ", ddlx='" + ddlx + '\'' +
                ", xdsj=" + xdsj +
                ", ddzt=" + ddzt +
                ", cp=" + cp +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
