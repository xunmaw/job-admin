package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 招聘信息
 */
public class Requirement implements Serializable {
    /**
     * 招聘需求编号
     */
    private Integer requireId;
    /**
     * 招聘岗位
     */
    private String requireJob;
    /**
     * 企业编号
     */
    private String enterpriseId;
    private Enterprise enterprise;
    /**
     * 需求专业
     */
    private String majorId;
    private Major major;
    /**
     * 需求人数
     */
    private String requireCount;
    /**
     * 工作地点
     */
    private Integer regionId;
    private Region region;
    /**
     * 薪资水平
     */
    private String requireSalary;
    /**
     * 技能需求
     */
    private String requireOther;
    /**
     * 审核状态
     */
    private String requireState;
    /**
     * 申请状态
     */
    private String apply;

    public Requirement() {
    }

    public Requirement(Integer requireId, String requireJob, String enterpriseId, String majorId, String requireCount, Integer regionId, String requireSalary, String requireOther, String requireState,String apply) {
        this.requireId = requireId;
        this.requireJob = requireJob;
        this.enterpriseId = enterpriseId;
        this.majorId = majorId;
        this.requireCount = requireCount;
        this.regionId = regionId;
        this.requireSalary = requireSalary;
        this.requireOther = requireOther;
        this.requireState = requireState;
        this.apply=apply;
    }

    public Integer getRequireId() {
        return requireId;
    }

    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
    }

    public String getRequireJob() {
        return requireJob;
    }

    public void setRequireJob(String requireJob) {
        this.requireJob = requireJob;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getRequireCount() {
        return requireCount;
    }

    public void setRequireCount(String requireCount) {
        this.requireCount = requireCount;
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

    public String getRequireSalary() {
        return requireSalary;
    }

    public void setRequireSalary(String requireSalary) {
        this.requireSalary = requireSalary;
    }

    public String getRequireOther() {
        return requireOther;
    }

    public void setRequireOther(String requireOther) {
        this.requireOther = requireOther;
    }

    public String getRequireState() {
        return requireState;
    }

    public void setRequireState(String requireState) {
        this.requireState = requireState;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "requireId='" + requireId + '\'' +
                ", requireJob='" + requireJob + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterprise=" + enterprise +
                ", majorId='" + majorId + '\'' +
                ", major=" + major +
                ", requireCount='" + requireCount + '\'' +
                ", regionId='" + regionId + '\'' +
                ", region=" + region +
                ", requireSalary='" + requireSalary + '\'' +
                ", requireOther='" + requireOther + '\'' +
                ", requireState='" + requireState + '\'' +
                '}';
    }
}
