package com.xunmaw.graduate.service;

import com.xunmaw.graduate.base.BaseService;
import com.xunmaw.graduate.entity.Item;

import java.util.List;

public interface ItemService extends BaseService<Item> {
    List<Item> selectByIntentionId(String intentionId);
}
