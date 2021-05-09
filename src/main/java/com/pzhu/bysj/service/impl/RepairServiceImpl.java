package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.RepairDao;
import com.pzhu.bysj.pojo.Repair;
import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    RepairDao repairDao;
    @Override
    public int addRepairInfo(Repair repair) {
        return repairDao.addRepairInfo(repair);
    }

    @Override
    public long totalCount() {
        return repairDao.totalCount();
    }

    @Override
    public List<Repair> findRepairinfos(HashMap<String, Object> map) {
        return repairDao.findRepairinfos(map);
    }

    @Override
    public List<RepairMan> findAllRepairMan() {
        return repairDao.findAllRepairMan();
    }

    @Override
    public int updateRepairInfo(Repair repair) {
        return repairDao.updateRepairInfo(repair);
    }

    @Override
    public int deleteRepairInfo(Integer id) {
        return repairDao.deleteRepairInfo(id);
    }

    @Override
    public int deleteRepairInfos(String[] split) {
        return repairDao.deleteRepairInfos(split);
    }

    @Override
    public Repair repair_findById(Integer id) {
        return repairDao.repair_findById(id);
    }

    @Override
    public List<Repair> user_findRepairInfo(Integer stu_id) {
        return repairDao.user_findRepairInfo(stu_id);
    }


}
