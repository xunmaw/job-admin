package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.EnterpriseType;

public interface EnterpriseTypeService extends BaseService<EnterpriseType> {
    String change(String name);
}
