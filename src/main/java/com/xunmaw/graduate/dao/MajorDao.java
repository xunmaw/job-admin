package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorDao extends BaseDao<Major> {
    List<Major> listByDepartId(Integer departId);
    String findIndex(@Param("sql")String sql);
}
