package com.xunmaw.graduate.vo;

import com.xunmaw.graduate.entity.*;

import java.io.Serializable;

public class StudentVo implements Serializable {
    private Integer stuNo;
    /**
     * 学号
     */
    private String stuId;
    /**
     * 姓名
     */
    private String stuName;
    /**
     * 性别
     */
    private String stuSex;
    /**
     *年龄
     */
    private String stuBirthday;
    private String stuAge;
    /**
     * 身份证号
     */
    private String stuCredit;
    /**
     * 政治面貌
     */
    private String politicalId;
    private Political political;
    /**
     * 所属院系id
     */
    private Integer departId;
    private Depart depart;
    /**
     * 专业id
     */
    private String majorId;
    private Major major;
    /**
     * 入学时间
     */
    private String stuEntrance;
    /**
     * 毕业时间
     */
    private String stuGraduTime;
    /**
     * 毕业去向
     */
    private String placeId;//1
    private GraduatePlace graduatePlace;
    /**
     * 联系方式
     */
    private String stuContr;
    /**
     * 密码
     */
    private String stuPass;

    /**
     * 就业状态
     * @return
     */
    private String stateId;
    private ObtainState obtainState;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuAge() {
        return stuAge;
    }

    public GraduatePlace getGraduatePlace() {
        return graduatePlace;
    }

    public void setGraduatePlace(GraduatePlace graduatePlace) {
        this.graduatePlace = graduatePlace;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuCredit() {
        return stuCredit;
    }

    public void setStuCredit(String stuCredit) {
        this.stuCredit = stuCredit;
    }

    public String getPoliticalId() {
        return politicalId;
    }

    public void setPoliticalId(String politicalId) {
        this.politicalId = politicalId;
    }

    public Political getPolitical() {
        return political;
    }

    public void setPolitical(Political political) {
        this.political = political;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getStuEntrance() {
        return stuEntrance;
    }

    public void setStuEntrance(String stuEntrance) {
        this.stuEntrance = stuEntrance;
    }

    public String getStuGraduTime() {
        return stuGraduTime;
    }

    public void setStuGraduTime(String stuGraduTime) {
        this.stuGraduTime = stuGraduTime;
    }

    public String getStuContr() {
        return stuContr;
    }

    public void setStuContr(String stuContr) {
        this.stuContr = stuContr;
    }

    public String getStuPass() {
        return stuPass;
    }

    public void setStuPass(String stuPass) {
        this.stuPass = stuPass;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public ObtainState getObtainState() {
        return obtainState;
    }

    public void setObtainState(ObtainState obtainState) {
        this.obtainState = obtainState;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
