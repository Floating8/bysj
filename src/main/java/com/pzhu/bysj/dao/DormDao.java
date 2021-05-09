package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.Dorm;
import com.pzhu.bysj.pojo.DormToShow;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DormDao {
    List<Dorm> findDorms();

    long totalCount();

    Dorm findDorm(Dorm dorm);

    Integer findBuildId(String dormId);

    List<Dorm> findDormList(HashMap<String, Object> map);

    String dormFindById(Integer id);

    Dorm findDormInfo(Integer id);

    int Dorm_add(Dorm dorm);

    int dorms_delete(String[] split);

    int dorm_delete(Integer id);
}
