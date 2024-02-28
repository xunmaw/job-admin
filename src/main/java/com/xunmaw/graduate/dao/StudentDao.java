package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentDao extends BaseDao<Student> {
    Integer selectCountBy(@Param("stuId") String stuId, @Param("stuEntrance") String stuEntrance, @Param("stateId") String stateId,@Param("majorId") String majorId);
    Integer count(@Param("sql")String sql,@Param("placeId") String placeId,@Param("stuGraduTime")String stuGraduTime,@Param("departId")Integer departId,@Param("majorId")String majorId);
}
