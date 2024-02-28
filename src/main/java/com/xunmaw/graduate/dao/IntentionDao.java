package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Intention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntentionDao extends BaseDao<Intention> {
    List<Intention> findByPage(@Param("stuId") String stuId, @Param("start") Integer start,@Param("size") Integer size);
    Integer selectCountBy(@Param("intentionMajor") String intentionMajor,@Param("regionId") String regionId);
    Integer count(@Param("sql")String sql,@Param("regionName")String regionName,@Param("intentionMajor")String intentionMajor);
}
