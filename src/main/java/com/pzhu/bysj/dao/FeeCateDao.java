package com.pzhu.bysj.dao;

import org.mapstruct.Mapper;

@Mapper
public interface FeeCateDao {
    String findFee(Integer id);

    int findFeeId(String remark);

}
