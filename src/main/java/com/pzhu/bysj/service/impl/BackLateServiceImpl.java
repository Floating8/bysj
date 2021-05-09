package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.BackLateDao;
import com.pzhu.bysj.pojo.BackLate;
import com.pzhu.bysj.service.BackLateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BackLateServiceImpl implements BackLateService {
    @Autowired
    BackLateDao backLateDao;

    @Override
    public long totalCount() {
        return backLateDao.totalCount();
    }

    @Override
    public List<BackLate> findBackLates(HashMap<String, Object> map) {
        return backLateDao.findBackLates(map);
    }

    @Override
    public int deleteBackLates(String[] split) {
        return backLateDao.deleteBackLates(split);
    }

    @Override
    public int deleteBackLate(Integer id) {
        return backLateDao.deleteBackLate(id);
    }
}
