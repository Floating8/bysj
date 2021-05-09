package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.FeeCate;
import com.pzhu.bysj.pojo.Qfee;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface QfeeDao {
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
