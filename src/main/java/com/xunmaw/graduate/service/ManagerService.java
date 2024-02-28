package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Manager;

public interface ManagerService extends BaseService<Manager> {
    Manager login(String managerId, String managerPass);
}
