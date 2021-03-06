package com.ljy.VO;

public class PicVO {

    private String alt;

    private Integer pid;

    private String src;

    private String thumb;

    public PicVO(String alt, Integer pid, String src, String thumb) {
        this.alt = alt;
        this.pid = pid;
        this.src = src;
        this.thumb = thumb;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


}
