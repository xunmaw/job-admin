package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionDao extends BaseDao<Region> {
    List<String> findAllRegionName(@Param("sql")String sql);
    Integer findMaxIndex(@Param("sql")String sql);
}
