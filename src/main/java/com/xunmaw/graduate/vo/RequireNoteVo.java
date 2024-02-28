package com.xunmaw.graduate.vo;

import java.io.Serializable;

public class RequireNoteVo implements Serializable {
    /**
     * 投递记录id
     */
    private String noteId;
    /**
     * 学号
     */
    private String stuId;
    /**
     * 招聘需求编号
     */
    private String requireId;
    /**
     * 公司编号
     */
    private String enterpriseId;
    /**
     * 状态
     */
    private String stateId;

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

    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
}
