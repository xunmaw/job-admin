package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Intention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntentionService extends BaseService<Intention> {
    List<Intention> findByPage(String stuId, Integer start, Integer size);
    Integer selectCountBy(String intentionMajor,String regionId);
    void checkStatus(Intention intention);
    Integer count(@Param("sql")String sql,@Param("regionName")String regionName,@Param("intentionMajor")String intentionMajor);
}