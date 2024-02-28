package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.ProvinceDao;
import com.xunmaw.graduate.entity.Province;
import com.xunmaw.graduate.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl extends BaseServiceImpl<Province> implements ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public BaseDao<Province> getBaseDao() {
        return provinceDao;
    }

    @Override
    public String findMaxIndex(String sql) {
        return provinceDao.findMaxIndex(sql);
    }
}
