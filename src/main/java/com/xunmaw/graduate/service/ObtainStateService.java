package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.ObtainState;

public interface ObtainStateService extends BaseService<ObtainState> {
    String change(String name);
}
