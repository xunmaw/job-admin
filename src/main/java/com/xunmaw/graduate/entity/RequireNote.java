package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 申请记录
 */
public class RequireNote implements Serializable {
    /**
     * 投递记录id
     */
    private String noteId;
    /**
     * 学号
     */
    private String stuId;
    private Student student;
    /**
     * 招聘需求编号
     */
    private Integer requireId;
    private Requirement requirement;
    /**
     * 公司编号
     */
    private String enterpriseId;
    private Enterprise enterprise;
    /**
     * 状态
     */
    private String stateId;
    private NoteState noteState;

    private String noteDay;

    public RequireNote() {
    }

    public RequireNote(String noteId, String stuId, Integer requireId, String enterpriseId, String stateId,String noteDay) {
        this.noteId = noteId;
        this.stuId = stuId;
        this.requireId = requireId;
        this.enterpriseId = enterpriseId;
        this.stateId = stateId;
        this.noteDay = noteDay;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
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

    public Integer getRequireId() {
        return requireId;
    }

    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
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

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public NoteState getNoteState() {
        return noteState;
    }

    public void setNoteState(NoteState noteState) {
        this.noteState = noteState;
    }

    public String getNoteDay() {
        return noteDay;
    }

    public void setNoteDay(String noteDay) {
        this.noteDay = noteDay;
    }

    @Override
    public String toString() {
        return "RequireNote{" +
                "noteId='" + noteId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", student=" + student +
                ", requireId='" + requireId + '\'' +
                ", requirement=" + requirement +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterprise=" + enterprise +
                ", stateId='" + stateId + '\'' +
                ", noteState=" + noteState +
                ", noteDay='" + noteDay + '\'' +
                '}';
    }
}
