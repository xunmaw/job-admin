package com.xunmaw.graduate.entity;

import java.io.Serializable;

public class GraduatePlace implements Serializable{
    private String placeId;
    private String placeName;

    public GraduatePlace() {
    }

    public GraduatePlace(String placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
