package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Province;
import org.apache.ibatis.annotations.Param;

public interface ProvinceDao extends BaseDao<Province> {
    String findMaxIndex(@Param("sql")String sql);
}
