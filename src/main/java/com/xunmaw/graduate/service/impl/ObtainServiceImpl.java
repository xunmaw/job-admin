package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.ObtainDao;
import com.xunmaw.graduate.entity.Obtain;
import com.xunmaw.graduate.service.ObtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtainServiceImpl extends BaseServiceImpl<Obtain> implements ObtainService {
    @Autowired
    private ObtainDao obtainDao;

    @Override
    public BaseDao<Obtain> getBaseDao() {
        return obtainDao;
    }

    @Override
    public List<Obtain> findByPage(String stuId, Integer start, Integer size) {
        return obtainDao.findByPage(stuId,start,size);
    }

    @Override
    public Integer selectCountBy(String stuId) {
        return obtainDao.selectCountBy(stuId);
    }
}
