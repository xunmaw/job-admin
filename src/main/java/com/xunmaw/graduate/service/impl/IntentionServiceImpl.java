package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.IntentionDao;
import com.xunmaw.graduate.entity.Intention;
import com.xunmaw.graduate.enumer.StateEnum;
import com.xunmaw.graduate.service.IntentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntentionServiceImpl extends BaseServiceImpl<Intention> implements IntentionService {

    @Autowired
    private IntentionDao intentionDao;

    @Override
    public BaseDao<Intention> getBaseDao() {
        return intentionDao;
    }


    public List<Intention> findByPage(String stuId, Integer start, Integer size){
        return intentionDao.findByPage(stuId,start,size);
    }

    @Override
    public Integer selectCountBy(String intentionMajor, String regionId) {
        return intentionDao.selectCountBy(intentionMajor,regionId);
    }

    @Override
    public void checkStatus(Intention intention) {
        String state = intention.getIntentionState();
        if (StateEnum.UNCHECKED.getCode().equals(state)){
            intention.setIntentionState(StateEnum.UNCHECKED.getMsg());
        }
        if (StateEnum.PASSED.getCode().equals(state)){
            intention.setIntentionState(StateEnum.PASSED.getMsg());
        }
        if (StateEnum.FAIL.getCode().equals(state)){
            intention.setIntentionState(StateEnum.FAIL.getMsg());
        }
    }

    @Override
    public Integer count(String sql, String regionName, String intentionMajor) {
        return intentionDao.count(sql,regionName,intentionMajor);
    }

}
