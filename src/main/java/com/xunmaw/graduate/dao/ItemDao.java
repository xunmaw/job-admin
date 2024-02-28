package com.xunmaw.graduate.dao;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.entity.Item;

import java.util.List;

public interface ItemDao extends BaseDao<Item> {
    List<Item> selectByIntentionId(String intentionId);
}
