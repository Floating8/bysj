package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.Repair;
import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.pojo.User;
import com.pzhu.bysj.service.RepairService;
import com.pzhu.bysj.service.UserService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class RepairController {
    @Autowired
    RepairService repairService;

    @PostMapping("/Repair_add")
    public ResultCommon addRepairInfo(Repair repair, HttpSession session) {
        User user = (User) session.getAttribute("login");
        repair.setUser(user);
        repair.setRepairNo("维修" + new SimpleDateFormat("YYYYMMDDHHmmSSsss").format(new Date()));
//        System.out.println("要新增的对象:"+repair);
        ResultCommon common;
        try {
            int count = repairService.addRepairInfo(repair);
            if (count > 0) {
                common = ResultCommon.success(ResultEnum.REPAIR_SUCCESS);
            } else {
                common = ResultCommon.fail(ResultEnum.REPAIR_FAIL);
            }
        } catch (Exception e) {
            System.out.println(e);
            common = ResultCommon.fail(ResultEnum.REPAIR_FAIL);
        }
        return common;
    }

    @PostMapping("/Repair_update")
    public ResultCommon updateRepairInfo(Repair repair) {
        ResultCommon result = null;
        try {
            int count = repairService.updateRepairInfo(repair);
            if (count > 0) {
                result = ResultCommon.success(ResultEnum.SUCCESS);
            } else {
                result = ResultCommon.fail(ResultEnum.FAIL);
            }
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @Autowired
    UserService userService;

    @GetMapping("/repair_list")
    public PageUtils repairList(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = repairService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat();
        List<Repair> repairinfos = repairService.findRepairinfos(map);
        for (Repair repairinfo : repairinfos) {
            User userInfo = userService.getUserNaka(repairinfo.getRepairedId());
            repairinfo.setUser(userInfo);
        }
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, repairinfos);
        return pageUtils;
    }

    @GetMapping("/repairman_findall")
    public ResultCommon repairman_findall() {
        ResultCommon result = null;
        try {
            List<RepairMan> repairMEN = repairService.findAllRepairMan();
            result = ResultCommon.success(ResultEnum.SUCCESS, repairMEN);
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/repair_delete")
    public ResultCommon repair_delete(Integer id) {
        ResultCommon result = null;
        try {
            int count = repairService.deleteRepairInfo(id);
            if (count>0){
                result = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
                result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/repairs_delete")
    public ResultCommon repairs_delete(String ids){
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count =  repairService.deleteRepairInfos(split);
        if (count>0){
            result = ResultCommon.success(ResultEnum.SUCCESS);
        }else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @GetMapping("/repair_findById")
    public ResultCommon repair_findById(Integer id){
        ResultCommon result = null;
        try {
            Repair repair = repairService.repair_findById(id);
            result = ResultCommon.success(ResultEnum.SUCCESS,repair);
        }catch (Exception e){
            result = ResultCommon.success(ResultEnum.FAIL);
        }
        return result;
    }

    @GetMapping("/user_findRepairInfo")
    public List<Repair> user_findRepairInfo(HttpSession session){
        List<Repair> repairList = null;
        try {
            User user= (User) session.getAttribute("login");
            repairList = repairService.user_findRepairInfo(user.getStu_ID());
        }catch (Exception e){
            repairList = null;
        }
        return  repairList;
    }


}
