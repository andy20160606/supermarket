package cn.youguang.dto;

public class HdllDto {

    private Long hdId;
    private String hdmc;  //产品名称
    private Long sycs;  //上月次数
    private Long jycs;  //本月次数

    public Long getHdId() {
        return hdId;
    }

    public void setHdId(Long hdId) {
        this.hdId = hdId;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
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
