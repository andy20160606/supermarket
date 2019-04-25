package cn.youguang.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 状态
 */


@Table
@Entity(name = "t_zt")
public class Zt extends IdEntity {

    private String ztmc; //状态名称


    private Integer status; // 0代表 下架 1代表上架


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zts")
    private List<Cp> cps;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Cp> getCps() {
        return cps;
    }

    public void setCps(List<Cp> cps) {
        this.cps = cps;
    }

    public String getZtmc() {
        return ztmc;
    }

    public void setZtmc(String ztmc) {
        this.ztmc = ztmc;
    }
}
