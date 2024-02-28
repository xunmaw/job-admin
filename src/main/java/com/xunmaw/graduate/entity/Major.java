package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 专业信息
 */
public class Major implements Serializable {
    /**
     * 专业编号
     */
    private String majorId;
    /**
     * 院系编号
     */
    private Integer departId;
    private Depart depart;
    /**
     * 专业名称
     */
    private String majorName;

    public Major() {
    }

    public Major(String majorId, Integer departId, String majorName) {
        this.majorId = majorId;
        this.departId = departId;
        this.majorName = majorName;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId='" + majorId + '\'' +
                ", departId='" + departId + '\'' +
                ", depart=" + depart +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
