package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.RegionDao;
import com.xunmaw.graduate.entity.Region;
import com.xunmaw.graduate.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl extends BaseServiceImpl<Region> implements RegionService {
    @Autowired
    private RegionDao regionDao;

    @Override
    public BaseDao<Region> getBaseDao() {
        return regionDao;
    }

    @Override
    public Integer change(String name) {
        String sql="select * from region where region_name like '%"+name+"%'";
        Region region = regionDao.getBySqlReturnEntity(sql);
        if (region!=null)
            return region.getRegionId();
        return null;
    }

    @Override
    public List<String> findAllRegionName(String sql) {
        return regionDao.findAllRegionName(sql);
    }

    @Override
    public Integer findMaxIndex(String sql) {
        return regionDao.findMaxIndex(sql);
    }
}
