package cn.youguang.dto;

public class CpllDto {


    private Long cpId;
    private String cpmc;  //产品名称
    private Long sycs;  //上月次数
    private Long jycs;  //本月次数

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public Long getSycs() {
        return sycs;
    }

    public void setSycs(Long sycs) {
        this.sycs = sycs;
    }

    public Long getJycs() {
        return jycs;
    }

    public void setJycs(Long jycs) {
        this.jycs = jycs;
    }
}
