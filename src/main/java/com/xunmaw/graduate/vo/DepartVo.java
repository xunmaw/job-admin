package com.xunmaw.graduate.vo;

import java.io.Serializable;

public class DepartVo implements Serializable {
    private Integer departId;
    private String departName;

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
}
