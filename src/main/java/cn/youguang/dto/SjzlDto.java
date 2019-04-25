package cn.youguang.dto;

import java.util.List;
import java.util.Map;

public class SjzlDto {

    private Map<String, Long> zlrs;  //总览人数

    private Map<String, Long> hlrs;//好礼人数

    private Map<String, Long> zfsl;  //转发数量

    private List<CpllDto> cpllDtos;

    private List<HdllDto> hdllDtos;

    public SjzlDto() {
    }

    public SjzlDto(Map<String, Long> zlrs, Map<String, Long> hlrs, Map<String, Long> zfsl, List<CpllDto> cpllDtos, List<HdllDto> hdllDtos) {
        this.zlrs = zlrs;
        this.hlrs = hlrs;
        this.zfsl = zfsl;
        this.cpllDtos = cpllDtos;
        this.hdllDtos = hdllDtos;
    }

    public Map<String, Long> getZlrs() {
        return zlrs;
    }

    public void setZlrs(Map<String, Long> zlrs) {
        this.zlrs = zlrs;
    }

    public Map<String, Long> getHlrs() {
        return hlrs;
    }

    public void setHlrs(Map<String, Long> hlrs) {
        this.hlrs = hlrs;
    }

    public Map<String, Long> getZfsl() {
        return zfsl;
    }

    public void setZfsl(Map<String, Long> zfsl) {
        this.zfsl = zfsl;
    }

    public List<CpllDto> getCpllDtos() {
        return cpllDtos;
    }

    public void setCpllDtos(List<CpllDto> cpllDtos) {
        this.cpllDtos = cpllDtos;
    }

    public List<HdllDto> getHdllDtos() {
        return hdllDtos;
    }

    public void setHdllDtos(List<HdllDto> hdllDtos) {
        this.hdllDtos = hdllDtos;
    }
}
