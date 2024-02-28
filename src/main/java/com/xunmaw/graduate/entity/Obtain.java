package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 求职信息
 */
public class Obtain implements Serializable {
    /**
     * 求职信息编号
     */
    private Integer obtainId;
    /**
     * 学生学号
     */
    private String stuId;
    private Student student;
    /**
     * 就业类型 0自主就业 1非自主就业
     */
    private String obtainType;
    /**
     * 就业公司
     */
    private String obtainEnterprise;
    /**
     * 就业职位
     */
    private String obtainJob;
    /**
     * 就业时间
     */
    private String obtainTime;

    public Obtain() {
    }

    public Obtain(Integer obtainId, String stuId, String obtainType, String obtainEnterprise, String obtainJob, String obtainTime) {
        this.obtainId = obtainId;
        this.stuId = stuId;
        this.obtainType = obtainType;
        this.obtainEnterprise = obtainEnterprise;
        this.obtainJob = obtainJob;
        this.obtainTime = obtainTime;
    }

    public Integer getObtainId() {
        return obtainId;
    }

    public void setObtainId(Integer obtainId) {
        this.obtainId = obtainId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getObtainType() {
        return obtainType;
    }

    public void setObtainType(String obtainType) {
        this.obtainType = obtainType;
    }

    public String getObtainEnterprise() {
        return obtainEnterprise;
    }

    public void setObtainEnterprise(String obtainEnterprise) {
        this.obtainEnterprise = obtainEnterprise;
    }

    public String getObtainJob() {
        return obtainJob;
    }

    public void setObtainJob(String obtainJob) {
        this.obtainJob = obtainJob;
    }

    public String getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(String obtainTime) {
        this.obtainTime = obtainTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Obtain{" +
                "obtainId='" + obtainId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", obtainType='" + obtainType + '\'' +
                ", obtainEnterprise='" + obtainEnterprise + '\'' +
                ", obtainJob='" + obtainJob + '\'' +
                ", obtainTime='" + obtainTime + '\'' +
                '}';
    }
}
