package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Region;

import java.util.List;

public interface RegionService extends BaseService<Region> {
    Integer change(String name);
    List<String> findAllRegionName(String sql);
    Integer findMaxIndex(String sql);
}
