package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.Dorm;
import com.pzhu.bysj.pojo.FeeCate;
import com.pzhu.bysj.pojo.Qfee;
import com.pzhu.bysj.service.DormService;
import com.pzhu.bysj.service.FeeCateService;
import com.pzhu.bysj.service.QfeeService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class QfeeController {
    @Autowired
    QfeeService qfeeService;

    @PostMapping("/pay_Qfee")
    public ResultCommon pay_Qfee(Integer id) {
        ResultCommon resultCommon = null;
        try {
            int count = qfeeService.pay_Qfee(id);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/qfee_list")
    public PageUtils qfee_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = qfeeService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Qfee> qfees = qfeeService.findQfees(map);
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, qfees);
        return pageUtils;
    }

    @PostMapping("/delete_Qfee")
    public ResultCommon deleteQfee(Integer id) {
        ResultCommon resultCommon = null;
        try {
            int count = qfeeService.deleteQfee(id);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/Qfee_deletes")
    public ResultCommon Qfee_deletes(String ids) {
        ResultCommon result = null;
        String[] split = ids.split(",");
        int count = qfeeService.deletes_Qfee(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @GetMapping("/Qfees_findById")
    public ResultCommon Qfees_findById(Integer id) {
        ResultCommon resultCommon = null;
        try {
            Qfee qfee = qfeeService.Qfees_findById(id);
            int count = feeCateService.findFeeId(qfee.getRemark());
            qfee.setRemark(count + "");
            resultCommon = ResultCommon.success(ResultEnum.SUCCESS, qfee);
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/qfee_findFees")
    public List<FeeCate> qfee_findFees() {

        return qfeeService.findAllFees();
    }

    @Autowired
    FeeCateService feeCateService;

    @PostMapping("/Qfees_update")
    public ResultCommon Qfees_update(Qfee qfee, FeeCate feeCate) {
        ResultCommon result = null;
        String feeName = feeCateService.findFee(Integer.valueOf(feeCate.getRemark()));
        qfee.setRemark(feeName);
        try {
            int count = qfeeService.updateQfee(qfee);
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
    DormService dormService;

    @PostMapping("/qfee_add")
    public ResultCommon qfee_add(Qfee qfee, FeeCate feeCate, Dorm dorm) {
        ResultCommon resultCommon = null;
//        Dorm dorm_find = dormService.findDorm(dorm);
        Dorm dorm_find = new Dorm();
        dorm_find.setId(Integer.parseInt(dorm.getDname()));
        String remark = feeCateService.findFee(Integer.parseInt(feeCate.getRemark()));
        qfee.setRemark(remark);
        qfee.setQfno("编号"+new SimpleDateFormat("YYYYMMDDHHmmSSsss").format(new Date()));
        qfee.setDorm(dorm_find);
        try {
            int count = qfeeService.qfee_add(qfee);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }
}
