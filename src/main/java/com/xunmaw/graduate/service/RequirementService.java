package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Requirement;

public interface RequirementService extends BaseService<Requirement> {
    Integer selectCountBy(String enterpriseId,String requireJob,String requireState);
}
