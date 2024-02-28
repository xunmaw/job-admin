package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerDao extends BaseDao<Manager> {
    Manager login(@Param("managerId") String managerId, @Param("managerPass") String managerPass);
}
