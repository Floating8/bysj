package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.FeeCateDao;
import com.pzhu.bysj.service.FeeCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeCateServiceImpl implements FeeCateService {
    @Autowired
    FeeCateDao feeCateDao;
    @Override
    public String findFee(Integer id) {
        return feeCateDao.findFee(id);
    }

    @Override
    public int findFeeId(String remark) {
        return feeCateDao.findFeeId(remark);
    }
}
