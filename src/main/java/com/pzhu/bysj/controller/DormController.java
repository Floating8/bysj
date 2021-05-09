package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.*;
import com.pzhu.bysj.service.AdminService;
import com.pzhu.bysj.service.DormService;
import com.pzhu.bysj.service.UserService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class DormController {
    @Autowired
    DormService dormService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;



    @GetMapping("/floor_finds")
    public ResultCommon floor_finds() {
        ResultCommon resultCommon = null;
        try {
            List<Dorm> dormList = dormService.findDorms();
            resultCommon = ResultCommon.success(ResultEnum.SUCCESS, dormList);
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/dorm_find")
    public List<Dorm> dorm_find() {
        return dormService.findDorms();
    }

    @GetMapping("/dorm_list")
    public PageUtils dorm_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = dormService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Dorm> dorms = dormService.findDormList(map);
        List<DormDisplay> dormDisplays = new ArrayList<>(dorms.size());

        for (int i = 0; i < dorms.size(); i++) {
            Dorm dorm = dorms.get(i);
            DormDisplay dormDisplay = new DormDisplay();
            dormDisplay.setId(dorm.getId());
            dormDisplay.setOwn(dorm.getOwn());
            dormDisplay.setDname(dorm.getDname());
            if (null != dorm.getChief()) {
                dormDisplay.setChief(userService.getUserInfo(dorm.getChief()).getName());
                dormDisplay.setOwner(dorm.getOwner().split(",").length + "人");
            }else {
                dormDisplay.setChief("无");
                dormDisplay.setOwner("无");
            }
            dormDisplay.setAdmin(adminService.AdminInfo(dormService.findAdminId(Integer.valueOf(dorm.getBuildid()))).getAname());
            dormDisplays.add(dormDisplay);
        }

        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, dormDisplays);
        return pageUtils;
    }

    @GetMapping("/dorm_findById")
    public ResultCommon dorm_findById(Integer id) {
        ResultCommon result = null;
        String nameList = "";
        try {
            String idList = dormService.dormFindById(id);
            String[] split = idList.split(",");
            for (int i = 0; i < split.length; i++) {
                User userInfo = userService.getUserInfo(Integer.valueOf(split[i]));
                if (i != 0) {
                    nameList = nameList + "," + userInfo.getName();
                } else {
                    nameList = nameList + userInfo.getName();
                }
            }
            DormToShow dormToShow = new DormToShow();
            Dorm dorm = dormService.findDormInfo(id);
            dormToShow.setId(id);
            dormToShow.setMember(nameList);
            dormToShow.setDname(dorm.getDname());
            result = ResultCommon.success(ResultEnum.SUCCESS, dormToShow);
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/Dorm_add")
    public ResultCommon Dorm_add(Dorm dorm, HttpSession httpSession) {
        ResultCommon resultCommon = null;

        try {
            String buildName = dormService.findBuildName(dorm.getBuildid());
            dorm.setOwn(buildName);
            int count = dormService.Dorm_add(dorm);
            if (count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/dorm_delete")
    public ResultCommon dorm_delete(Integer id){
        ResultCommon result = null;
        try {
            int count = dormService.dorm_delete(id);
            if (count > 0) {
                result = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/dorms_delete")
    public ResultCommon dorms_delete(String ids){
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = dormService.dorms_delete(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }
}
