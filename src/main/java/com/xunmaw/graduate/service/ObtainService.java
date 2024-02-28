package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Obtain;

import java.util.List;

public interface ObtainService extends BaseService<Obtain> {
    List<Obtain> findByPage(String stuId,Integer start,Integer size);
    Integer selectCountBy(String stuId);
}
