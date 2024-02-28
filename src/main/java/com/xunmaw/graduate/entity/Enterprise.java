package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 企业信息
 */
public class Enterprise implements Serializable {
    private Integer enterpriseNo;
    /**
     * 企业编码
     */
    private String enterpriseId;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业所在地
     */
    private Integer regionId;
    private Region region;
    /**
     * 企业类型
     */
    private String typeId;
    private EnterpriseType enterpriseType;
    /**
     * 企业简介
     */
    private String enterpriseIntro;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系方式
     */
    private String contactTel;
    /**
     * 联系邮件
     */
    private String contactEmail;
    /**
     * 密码
     */
    private String enterprisePass;

    public Enterprise() {
    }

    public Enterprise(Integer enterpriseNo, String enterpriseId, String enterpriseName, Integer regionId, String typeId, String enterpriseIntro, String contactName, String contactTel, String contactEmail, String enterprisePass) {
        this.enterpriseNo = enterpriseNo;
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.regionId = regionId;
        this.typeId = typeId;
        this.enterpriseIntro = enterpriseIntro;
        this.contactName = contactName;
        this.contactTel = contactTel;
        this.contactEmail = contactEmail;
        this.enterprisePass = enterprisePass;
    }

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
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

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseNo=" + enterpriseNo +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", regionId='" + regionId + '\'' +
                ", region=" + region +
                ", typeId='" + typeId + '\'' +
                ", enterpriseType=" + enterpriseType +
                ", enterpriseIntro='" + enterpriseIntro + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", enterprisePass='" + enterprisePass + '\'' +
                '}';
    }
}
