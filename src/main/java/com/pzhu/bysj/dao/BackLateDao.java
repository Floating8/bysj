package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.BackLate;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BackLateDao {
    long totalCount();

    List<BackLate> findBackLates(HashMap<String, Object> map);

    int deleteBackLates(String[] split);

    int deleteBackLate(Integer id);
}
