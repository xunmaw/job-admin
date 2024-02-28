package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.StudentDao;
import com.xunmaw.graduate.entity.Student;
import com.xunmaw.graduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public BaseDao<Student> getBaseDao() {
        return studentDao;
    }

    @Override
    public Student login(String stuId,String passWord) {
        String sql="select * from student where stu_id='"+stuId+"' and stu_pass='"+passWord+"'";
        return studentDao.getBySqlReturnEntity(sql);
    }

    @Override
    public Integer selectCountBy(String stuId,String stuEntrance,String stateId,String majorId){
        return studentDao.selectCountBy(stuId,stuEntrance,stateId,majorId);
    }

    @Override
    public Integer count(String sql, String placeId, String stuGraduTime, Integer departId, String majorId) {
        return studentDao.count(sql,placeId,stuGraduTime,departId,majorId);
    }

}
