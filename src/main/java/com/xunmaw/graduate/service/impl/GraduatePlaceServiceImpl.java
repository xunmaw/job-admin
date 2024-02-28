package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.GraduatePlaceDao;
import com.xunmaw.graduate.entity.GraduatePlace;
import com.xunmaw.graduate.service.GraduatePlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraduatePlaceServiceImpl extends BaseServiceImpl<GraduatePlace> implements GraduatePlaceService {
    @Autowired
    private GraduatePlaceDao graduatePlaceDao;
    @Override
    public BaseDao<GraduatePlace> getBaseDao() {
        return graduatePlaceDao;
    }

    @Override
    public String change(String placeName) {
        return graduatePlaceDao.change(placeName);
    }
}
