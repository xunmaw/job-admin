package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Depart;

public interface DepartService extends BaseService<Depart> {
    Integer change(String name);
}
