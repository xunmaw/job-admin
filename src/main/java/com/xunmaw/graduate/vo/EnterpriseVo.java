package com.xunmaw.graduate.vo;

public class EnterpriseVo {
    private Integer enterpriseNo;
    /**
     * 企业编码
     */
    private String enterpriseId;//
    /**
     * 企业名称
     */
    private String enterpriseName;//
    /**
     * 企业所在地
     */
    private Integer regionId;//
    /**
     * 企业类型
     */
    private String typeId;//
    /**
     * 企业简介
     */
    private String enterpriseIntro;//
    /**
     * 联系人名称
     */
    private String contactName;//
    /**
     * 联系方式
     */
    private String contactTel;//
    /**
     * 联系邮件
     */
    private String contactEmail;//
    /**
     * 密码
     */
    private String enterprisePass;//

    public Integer getEnterpriseNo() {
        return enterpriseNo;
    }

    public void setEnterpriseNo(Integer enterpriseNo) {
        this.enterpriseNo = enterpriseNo;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getEnterpriseIntro() {
        return enterpriseIntro;
    }

    public void setEnterpriseIntro(String enterpriseIntro) {
        this.enterpriseIntro = enterpriseIntro;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getEnterprisePass() {
        return enterprisePass;
    }

    public void setEnterprisePass(String enterprisePass) {
        this.enterprisePass = enterprisePass;
    }
}
