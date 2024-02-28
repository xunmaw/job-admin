package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentService extends BaseService<Student> {
    Student login(String stuId,String passWord);
    Integer selectCountBy(String stuId,String stuEntrance,String stateId,String majorId);
    Integer count(@Param("sql")String sql,@Param("placeId") String placeId,@Param("stuGraduTime")String stuGraduTime,@Param("departId")Integer departId,@Param("majorId")String majorId);

}
