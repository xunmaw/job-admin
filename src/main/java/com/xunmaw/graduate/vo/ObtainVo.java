package com.xunmaw.graduate.vo;

public class ObtainVo {
    /**
     * 求职信息编号
     */
    private Integer obtainId;
    /**
     * 学生学号
     */
    private String stuId;
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
}
