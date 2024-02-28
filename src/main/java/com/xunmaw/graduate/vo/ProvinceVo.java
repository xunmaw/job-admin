package com.xunmaw.graduate.vo;

import java.io.Serializable;

public class ProvinceVo implements Serializable {
    private String provinceName;
    private Integer provinceId;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
