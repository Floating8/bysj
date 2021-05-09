package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.Repair;
import com.pzhu.bysj.pojo.RepairMan;

import java.util.HashMap;
import java.util.List;

public interface RepairService {
    int addRepairInfo(Repair repair);

    long totalCount();

    List<Repair> findRepairinfos(HashMap<String, Object> map);

    List<RepairMan> findAllRepairMan();

    int updateRepairInfo(Repair repair);

    int deleteRepairInfo(Integer id);

    int deleteRepairInfos(String[] split);

    Repair repair_findById(Integer id);

    List<Repair> user_findRepairInfo(Integer stu_id);
}
