package com.xunmaw.graduate.vo;

import com.xunmaw.graduate.entity.Region;
import com.xunmaw.graduate.entity.Student;

import java.io.Serializable;

public class IntentionVo implements Serializable {
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


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Intention{" +
                "intention_id='" + intentionId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", student=" + student +
                ", regionId='" + regionId + '\'' +
                ", region=" + region +
                ", intentionSalary=" + intentionSalary +
                ", intentionMajor='" + intentionMajor + '\'' +
                ", intentionOther='" + intentionOther + '\'' +
                ", intentionState='" + intentionState + '\'' +
                '}';
    }
}
