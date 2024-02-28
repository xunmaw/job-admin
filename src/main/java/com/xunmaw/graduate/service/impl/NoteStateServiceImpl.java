package com.xunmaw.graduate.service.impl;

import com.xunmaw.graduate.base.BaseDao;
import com.xunmaw.graduate.base.BaseServiceImpl;
import com.xunmaw.graduate.dao.NoteStateDao;
import com.xunmaw.graduate.entity.NoteState;
import com.xunmaw.graduate.service.NoteStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteStateServiceImpl extends BaseServiceImpl<NoteState> implements NoteStateService {
    @Autowired
    private NoteStateDao noteStateDao;

    @Override
    public BaseDao<NoteState> getBaseDao() {
        return noteStateDao;
    }
}
