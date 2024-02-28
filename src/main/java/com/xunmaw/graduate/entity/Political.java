package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 政治面貌
 */
public class Political implements Serializable {
    /**
     * 政治面貌编号
     */
    private String politicalId;
    /**
     * 政治面貌名称
     */
    private String politicalName;

    public Political() {
    }

    public Political(String politicalId, String politicalName) {
        this.politicalId = politicalId;
        this.politicalName = politicalName;
    }

    public String getPoliticalId() {
        return politicalId;
    }

    public void setPoliticalId(String politicalId) {
        this.politicalId = politicalId;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    @Override
    public String toString() {
        return "Political{" +
                "politicalId='" + politicalId + '\'' +
                ", politicalName='" + politicalName + '\'' +
                '}';
    }
}
