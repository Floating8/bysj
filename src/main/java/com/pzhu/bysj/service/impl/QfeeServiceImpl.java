package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.QfeeDao;
import com.pzhu.bysj.pojo.FeeCate;
import com.pzhu.bysj.pojo.Qfee;
import com.pzhu.bysj.service.QfeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QfeeServiceImpl implements QfeeService {
    @Autowired
    QfeeDao qfeeDao;
    @Override
    public List<Qfee> findByDormId(Integer id) {
        return qfeeDao.findByDormId(id);
    }

    @Override
    public int pay_Qfee(Integer id) {
        return qfeeDao.pay_Qfee(id);
    }

    @Override
    public long totalCount() {
        return qfeeDao.totalCount();
    }

    @Override
    public List<Qfee> findQfees(HashMap<String, Object> map) {
        return qfeeDao.findQfees(map);
    }

    @Override
    public int deleteQfee(Integer id) {
        return qfeeDao.deleteQfee(id);
    }

    @Override
    public int deletes_Qfee(String[] split) {
        return qfeeDao.deletes_Qfee(split);
    }

    @Override
    public Qfee Qfees_findById(Integer id) {
        return qfeeDao.Qfees_findById(id);
    }

    @Override
    public List<FeeCate> findAllFees() {
        return qfeeDao.findAllFees();
    }

    @Override
    public int updateQfee(Qfee qfee) {
        return qfeeDao.updateQfee(qfee);
    }

    @Override
    public int qfee_add(Qfee qfee) {
        return qfeeDao.qfee_add(qfee);
    }
}
