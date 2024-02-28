package com.xunmaw.graduate.entity;

import java.io.Serializable;

/**
 * 地区信息
 */
public class Region implements Serializable {
    /**
     * 地区编码
     */
    private Integer regionId;
    /**
     * 省份编码
     */
    private Integer provinceId;
    private Province province;
    /**
     * 地区名称
     */
    private String regionName;

    public Region() {
    }

    public Region(Integer regionId, Integer provinceId, String regionName) {
        this.regionId = regionId;
        this.provinceId = provinceId;
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId='" + regionId + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", province=" + province +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
