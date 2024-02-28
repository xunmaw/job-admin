package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Obtain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ObtainDao extends BaseDao<Obtain> {
    List<Obtain> findByPage(@Param("stuId") String stuId, @Param("start") Integer start, @Param("size") Integer size);
    Integer selectCountBy(@Param("stuId") String stuId);
}
