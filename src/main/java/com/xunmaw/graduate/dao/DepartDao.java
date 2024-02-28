package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Depart;
import org.apache.ibatis.annotations.Select;

public interface DepartDao extends BaseDao<Depart> {
    @Select("select departName from depart where depart_id=#{departId}")
    String selectNameById(String departId);
}
