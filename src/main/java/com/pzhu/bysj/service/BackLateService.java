package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.BackLate;

import java.util.HashMap;
import java.util.List;

public interface BackLateService {
    long totalCount();

    List<BackLate> findBackLates(HashMap<String, Object> map);

    int deleteBackLates(String[] split);

    int deleteBackLate(Integer id);
}
