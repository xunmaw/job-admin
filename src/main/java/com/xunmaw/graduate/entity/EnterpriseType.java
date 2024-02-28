package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 企业类型
 */
public class EnterpriseType implements Serializable {
    private String typeId;
    private String typeName;

    public EnterpriseType() {
    }

    public EnterpriseType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "EnterpriseType{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
