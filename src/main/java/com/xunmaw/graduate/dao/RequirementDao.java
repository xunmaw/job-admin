package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Requirement;
import org.apache.ibatis.annotations.Param;

public interface RequirementDao extends BaseDao<Requirement> {
    Integer selectCountBy(@Param("enterpriseId") String enterpriseId,@Param("requireJob") String requireJob,@Param("requireState") String requireState);
}
