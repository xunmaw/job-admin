package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.GraduatePlace;

public interface GraduatePlaceService extends BaseService<GraduatePlace> {
    String change(String placeName);
}
