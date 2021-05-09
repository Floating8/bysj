package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.FeeCate;
import com.pzhu.bysj.pojo.Qfee;

import java.util.HashMap;
import java.util.List;

public interface QfeeService {
    List<Qfee> findByDormId(Integer id);

    int pay_Qfee(Integer id);

    long totalCount();

    List<Qfee> findQfees(HashMap<String, Object> map);

    int deleteQfee(Integer id);

    int deletes_Qfee(String[] split);

    Qfee Qfees_findById(Integer id);

    List<FeeCate> findAllFees();

    int updateQfee(Qfee qfee);

    int qfee_add(Qfee qfee);
}
