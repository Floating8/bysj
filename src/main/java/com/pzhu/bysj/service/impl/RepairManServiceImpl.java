package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.RepairManDao;
import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.pojo.User;
import com.pzhu.bysj.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RepairManServiceImpl implements RepairManService {
    @Autowired
    RepairManDao repairManDao;

    @Override
    public long totalCount() {
        return repairManDao.totalCount();
    }

    @Override
    public List<RepairMan> findRepairMan(HashMap<String, Object> map) {
        return repairManDao.findRepairMan(map);
    }

    @Override
    public RepairMan repairman_findById(Integer id) {
        return repairManDao.repairman_findById(id);
    }

    @Override
    public int repairman_update(RepairMan repairMan) {
        return repairManDao.repairman_update(repairMan);
    }

    @Override
    public int repairman_add(RepairMan repairMan) {
        return repairManDao.repairman_add(repairMan);
    }

    @Override
    public int repairman_delete(Integer id) {
        return repairManDao.repairman_delete(id);
    }

    @Override
    public int repairers_delete(String[] split) {
        return repairManDao.repairers_delete(split);
    }
}
