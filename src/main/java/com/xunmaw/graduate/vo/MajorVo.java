package com.xunmaw.graduate.vo;

import java.io.Serializable;

public class MajorVo implements Serializable {
    private String majorId;
    private String majorName;
    private Integer departId;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }
}
