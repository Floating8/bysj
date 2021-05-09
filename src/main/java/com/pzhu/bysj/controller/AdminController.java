package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.Admin;
import com.pzhu.bysj.pojo.User;
import com.pzhu.bysj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin_login")
    public ResultCommon getAdminLogin(Admin admin, String code, HttpSession session){
        ResultCommon result = null;
        Admin login = adminService.login(admin);
        String safeCode = (String) session.getAttribute("safeCode");
        if (!safeCode.equalsIgnoreCase(code)) {
            result = ResultCommon.fail(ResultEnum.FAIL_CODE_NOT_TRUE);
        } else {
            if (login != null) {
                session.setAttribute("admin", login);
                result = ResultCommon.success(ResultEnum.SUCCESS);
            } else {
                result = ResultCommon.fail(ResultEnum.FAIL);
            }
        }
        return result;
    }

    @GetMapping("/admin_getLoginUser")
    public ResultCommon getLoginUser(HttpSession session){
        ResultCommon result = null;
        try {
            Admin login = (Admin) session.getAttribute("admin");
            result = ResultCommon.success(login);
        }catch (Exception e){
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @Autowired
    UserService userService;
    @Autowired
    RepairService repairService;
    @Autowired
    DormService dormService;
    @Autowired
    LeaveService leaveService;

    @GetMapping("/adminIndex_querySystemDatas")
    public Map<String,Object> adminIndex_querySystemDatas(){
        Map<String,Object> map = new HashMap();
        long dormTotalCount = dormService.totalCount();
        long userTotalCount = userService.totalCount();
        long repairTotalCount = repairService.totalCount();
        long leaveTotalCount = leaveService.totalCount();
        map.put("dormTotalCount",dormTotalCount);
        map.put("userTotalCount",userTotalCount);
        map.put("repairTotalCount",repairTotalCount);
        map.put("leaveTotalCount",leaveTotalCount);
        return map;
    }

    @GetMapping("/admin_Logout")
    public ResultCommon admin_Logout(HttpSession session){
        ResultCommon result = null;
        try {
            session.removeAttribute("admin");
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
            return result;
        }
        result = ResultCommon.success(ResultEnum.SUCCESS);
        return result;
    }

}
