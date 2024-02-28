package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Province;
import org.apache.ibatis.annotations.Param;

public interface ProvinceService extends BaseService<Province> {
    String findMaxIndex(@Param("sql")String sql);
}
