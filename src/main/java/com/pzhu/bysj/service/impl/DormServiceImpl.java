package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.BuildingDao;
import com.pzhu.bysj.dao.DormDao;
import com.pzhu.bysj.pojo.Dorm;
import com.pzhu.bysj.pojo.DormToShow;
import com.pzhu.bysj.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DormServiceImpl implements DormService {
    @Autowired
    DormDao dormDao;

    @Autowired
    BuildingDao buildingDao;

    @Override
    public List<Dorm> findDorms() {
        return dormDao.findDorms();
    }

    @Override
    public long totalCount() {
        return dormDao.totalCount();
    }

    @Override
    public Dorm findDorm(Dorm dorm) {
        return dormDao.findDorm(dorm);
    }

    @Override
    public Integer findBuildId(String dormId) {
        return dormDao.findBuildId(dormId);
    }

    @Override
    public Integer findAdminId(Integer buildId) {
        return buildingDao.findAdminId(buildId);
    }

    @Override
    public List<Dorm> findDormList(HashMap<String, Object> map) {
        return dormDao.findDormList(map);
    }

    @Override
    public String dormFindById(Integer id) {
        return dormDao.dormFindById(id);
    }

    @Override
    public Dorm findDormInfo(Integer id) {
        return dormDao.findDormInfo(id);
    }

    @Override
    public int Dorm_add(Dorm dorm) {
        return dormDao.Dorm_add(dorm);
    }

    @Override
    public String findBuildName(String buildid) {
        return buildingDao.findBuildName(buildid);
    }

    @Override
    public int dorms_delete(String[] split) {
        return dormDao.dorms_delete(split);
    }

    @Override
    public int dorm_delete(Integer id) {
        return dormDao.dorm_delete(id);
    }
}
