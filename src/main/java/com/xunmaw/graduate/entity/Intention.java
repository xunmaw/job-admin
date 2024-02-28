package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 求职意向信息
 */
public class Intention implements Serializable {
    /**
     * 求职意向编号
     */
    private Integer intentionId;
    /**
     * 学生编号
     */
    private String stuId;
    private Student student;
    /**
     * 意向工作地点
     */
    private Integer regionId;
    private Region region;
    /**
     * 期望薪资
     */
    private String intentionSalary;
    /**
     * 期望岗位
     */
    private String intentionMajor;
    /**
     * 掌握技能
     */
    private String intentionOther;
    /**
     * 审核状态
     */
    private String intentionState;
    /**
     * 发布状态
     */
    private String releaseState;

    public Intention() {
    }

    public Intention(Integer intentionId, String stuId, Student student, Integer regionId, Region region, String intentionSalary, String intentionMajor, String intentionOther, String intentionState, String releaseState) {
        this.intentionId = intentionId;
        this.stuId = stuId;
        this.student = student;
        this.regionId = regionId;
        this.region = region;
        this.intentionSalary = intentionSalary;
        this.intentionMajor = intentionMajor;
        this.intentionOther = intentionOther;
        this.intentionState = intentionState;
        this.releaseState = releaseState;
    }

    public Integer getIntentionId() {
        return intentionId;
    }

    public void setIntentionId(Integer intentionId) {
        this.intentionId = intentionId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getIntentionSalary() {
        return intentionSalary;
    }

    public void setIntentionSalary(String intentionSalary) {
        this.intentionSalary = intentionSalary;
    }

    public String getIntentionMajor() {
        return intentionMajor;
    }

    public void setIntentionMajor(String intentionMajor) {
        this.intentionMajor = intentionMajor;
    }

    public String getIntentionOther() {
        return intentionOther;
    }

    public void setIntentionOther(String intentionOther) {
        this.intentionOther = intentionOther;
    }

    public String getIntentionState() {
        return intentionState;
    }

    public void setIntentionState(String intentionState) {
        this.intentionState = intentionState;
    }

    public String getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
    }

    @Override
    public String toString() {
        return "Intention{" +
                "intentionId='" + intentionId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", student=" + student +
                ", regionId='" + regionId + '\'' +
                ", region=" + region +
                ", intentionSalary='" + intentionSalary + '\'' +
                ", intentionMajor='" + intentionMajor + '\'' +
                ", intentionOther='" + intentionOther + '\'' +
                ", intentionState='" + intentionState + '\'' +
                ", releaseState='" + releaseState + '\'' +
                '}';
    }
}
