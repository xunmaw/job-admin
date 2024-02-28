package com.xunmaw.graduate.entity;


import java.io.Serializable;

/**
 * 院系信息
 */
public class Depart implements Serializable {

    /**
     * 院系编码
     */
    private Integer departId;
    /**
     * 院系名称
     */
    private String departName;

    public Depart(){
    }

    public Depart(Integer departId,String departName)
    {
        this.departId=departId;
        this.departName=departName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "departId='" + departId + '\'' +
                ", departName='" + departName + '\'' +
                '}';
    }
}
