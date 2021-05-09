package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.pojo.User;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RepairManDao {
    long totalCount();

    List<RepairMan> findRepairMan(HashMap<String, Object> map);

    RepairMan repairman_findById(Integer id);

    int repairman_update(RepairMan repairMan);

    int repairman_add(RepairMan repairMan);

    int repairman_delete(Integer id);

    int repairers_delete(String[] split);

}
