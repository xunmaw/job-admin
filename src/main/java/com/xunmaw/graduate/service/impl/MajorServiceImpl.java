package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.MajorDao;
import com.xunmaw.graduate.entity.Major;
import com.xunmaw.graduate.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl extends BaseServiceImpl<Major> implements MajorService {
    @Autowired
    private MajorDao majorDao;

    @Override
    public BaseDao<Major> getBaseDao() {
        return majorDao;
    }

    @Override
    public List<Major> listByDepartId(Integer departId) {
        return majorDao.listByDepartId(departId);
    }

    @Override
    public String change(String name, Integer departId) {
        String sql="select * from major where major_name like '%"+name+"%' and depart_id="+departId;
        Major major=majorDao.getBySqlReturnEntity(sql);
        if (major!=null)
            return major.getMajorId();
        return null;
    }

    @Override
    public String findIndex(String sql) {
        return majorDao.findIndex(sql);
    }
}
