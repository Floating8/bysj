package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.*;
import com.pzhu.bysj.service.LeaveService;
import com.pzhu.bysj.service.UserService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @PostMapping("/add_leave")
    public ResultCommon add_leave(Leave leave, HttpSession session) {
        User login = (User) session.getAttribute("login");
        leave.setUser(login);
        ResultCommon resultCommon = null;
        try {
            int count = leaveService.add_leave(leave);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/agree_leave")
    public ResultCommon agree_leave(Integer id) {
        ResultCommon resultCommon = null;
        try {
            int count = leaveService.agree_leave(id);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/leaves_list")
    public PageUtils leaves_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = leaveService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Leave> leaves = leaveService.findLeaves(map);
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, leaves);
        return pageUtils;
    }

    @GetMapping("/leave_delete")
    public ResultCommon leave_delete(Integer id){
        ResultCommon resultCommon = null;
        try {
            int count = leaveService.leave_delete(id);
            if (count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }else {
                resultCommon = ResultCommon.fail(ResultEnum.FAIL);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/leaves_delete")
    public ResultCommon leaves_delete(String ids) {
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = leaveService.leaves_delete(split);
        try {
            if (count > 0) {
                result = ResultCommon.success(ResultEnum.SUCCESS);
            } else {
                result = ResultCommon.fail(ResultEnum.FAIL);
            }
        }catch (Exception e){
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }



    @Autowired
    UserService userService;

    @GetMapping("/user_findLeaveInfo")
    public List<LeaveToShow> user_findRepairInfo(HttpSession session){
        List<LeaveToShow> LeaveList = null;
        try {
            User user= (User) session.getAttribute("login");
            LeaveList = leaveService.user_findRepairInfo(user.getStu_ID());
            for (int i = 0; i < LeaveList.size(); i++) {
                LeaveList.get(i).setStuName(userService.getUserInfo(user.getId()).getName());
            }
        }catch (Exception e){
            LeaveList = null;
        }
        return  LeaveList;
    }
}
