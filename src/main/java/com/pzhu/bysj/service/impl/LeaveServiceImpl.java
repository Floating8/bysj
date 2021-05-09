package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.LeaveDao;
import com.pzhu.bysj.pojo.Leave;
import com.pzhu.bysj.pojo.LeaveToShow;
import com.pzhu.bysj.pojo.News;
import com.pzhu.bysj.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    LeaveDao leaveDao;

    @Override
    public long totalCount() {
        return leaveDao.totalCount();
    }

    @Override
    public int add_leave(Leave leave) {
        return leaveDao.add_leave(leave);
    }

    @Override
    public List<Leave> findLeaves(HashMap<String, Object> map) {
        return leaveDao.findLeaves(map);
    }

    @Override
    public int agree_leave(Integer id) {
        return leaveDao.agree_leave(id);
    }

    @Override
    public int leave_delete(Integer id) {
        return leaveDao.leave_delete(id);
    }

    @Override
    public int leaves_delete(String[] split) {
        return leaveDao.leaves_delete(split);
    }

    @Override
    public List<Leave> findUnprocessed() {
        return leaveDao.findUnprocessed();
    }

    @Override
    public List<LeaveToShow> user_findRepairInfo(Integer stu_id) {
        return leaveDao.user_findRepairInfo(stu_id);
    }
}
