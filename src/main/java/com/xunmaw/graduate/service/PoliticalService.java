package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Political;

public interface PoliticalService extends BaseService<Political> {
    String change(String name);
}
