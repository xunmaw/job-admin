package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Enterprise;

public interface EnterpriseService extends BaseService<Enterprise> {
    Enterprise login(String stuId, String passWord);
}
