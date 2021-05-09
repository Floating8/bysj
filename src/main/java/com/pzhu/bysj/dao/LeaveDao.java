package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.Leave;
import com.pzhu.bysj.pojo.LeaveToShow;
import com.pzhu.bysj.pojo.News;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface LeaveDao {
    long totalCount();

    int add_leave(Leave leave);

    List<Leave> findLeaves(HashMap<String, Object> map);

    int agree_leave(Integer id);

    int leave_delete(Integer id);

    int leaves_delete(String[] split);

    List<Leave> findUnprocessed();

    List<LeaveToShow> user_findRepairInfo(Integer stu_id);
}
