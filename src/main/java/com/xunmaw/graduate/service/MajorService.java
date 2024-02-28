package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Major;

import java.util.List;

public interface MajorService extends BaseService<Major> {
    List<Major> listByDepartId(Integer departId);
    String change(String name,Integer departId);
    String findIndex(String sql);
}
