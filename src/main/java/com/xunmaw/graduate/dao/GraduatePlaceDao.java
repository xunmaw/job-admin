package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.GraduatePlace;
import org.apache.ibatis.annotations.Param;

public interface GraduatePlaceDao extends BaseDao<GraduatePlace> {
    String change(@Param("placeName") String placeName);
}
