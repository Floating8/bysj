package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.Repair;
import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.service.RepairService;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RepairDao {
    int addRepairInfo(Repair repair);

    long totalCount();

    List<Repair> findRepairinfos(HashMap<String, Object> map);

    List<RepairMan> findAllRepairMan();

    int updateRepairInfo(Repair repair);

    int deleteRepairInfos(String[] split);

    int deleteRepairInfo(Integer id);

    Repair repair_findById(Integer id);

    List<Repair> user_findRepairInfo(Integer repairedid);
}
