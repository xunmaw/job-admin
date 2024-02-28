package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 省份信息
 */
public class Province implements Serializable {
    /**
     * 省份编号
     */
    private Integer provinceId;
    /**
     * 省份名称
     */
    private String provinceName;

    public Province() {
    }

    public Province(Integer provinceId, String provinceName) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
