package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.Dorm;
import com.pzhu.bysj.pojo.DormToShow;

import java.util.HashMap;
import java.util.List;

public interface DormService {
    List<Dorm> findDorms();

    long totalCount();

    Dorm findDorm(Dorm dorm);

    Integer findBuildId(String dormId);

    Integer findAdminId(Integer buildId);

    List<Dorm> findDormList(HashMap<String, Object> map);

    String dormFindById(Integer id);

    Dorm findDormInfo(Integer id);

    int Dorm_add(Dorm dorm);

    String findBuildName(String buildid);

    int dorms_delete(String[] split);

    int dorm_delete(Integer id);
}
