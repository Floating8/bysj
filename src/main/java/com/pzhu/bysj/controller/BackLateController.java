package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.BackLate;
import com.pzhu.bysj.pojo.News;
import com.pzhu.bysj.service.BackLateService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BackLateController {

    @Autowired
    BackLateService backLateService;

    @GetMapping("/backlate_list")
    public PageUtils backlate_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = backLateService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<BackLate> backLates = backLateService.findBackLates(map);
       /* for (News news1 : news) {
            news1.setCreatetime((news1.getCreatetime().split("\\."))[0]);
            news1.setUpdatetime((news1.getUpdatetime().split("\\."))[0]);
        }*/
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, backLates);
        return pageUtils;
    }

    @PostMapping("/backlates_delete")
    public ResultCommon backlates_delete(String ids) {
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = backLateService.deleteBackLates(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }


    @GetMapping("/backlate_delete")
    public ResultCommon backlate_delete(Integer id) {
        ResultCommon result = null;
        try {
            int count = backLateService.deleteBackLate(id);
            if (count > 0) {
                result = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }
}
