package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.RepairMan;
import com.pzhu.bysj.pojo.User;
import com.pzhu.bysj.service.RepairManService;
import com.pzhu.bysj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class RepairManController {
    @Autowired
    RepairManService repairManService;

    @GetMapping("/repairman_list")
    public PageUtils repairman_list(@RequestParam(defaultValue = "1") Long pageIndex,
                                    @RequestParam(defaultValue = "8") Long pageSize) {
        PageUtils pageUtils = null;
        long totalCount = repairManService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<RepairMan> repairManList = repairManService.findRepairMan(map);
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        pageUtils = new PageUtils(pageIndex, pageSize, totalCount, repairManList);
        return pageUtils;
    }

    @GetMapping("/repairman_findById")
    public ResultCommon repairman_findById(Integer id){
        ResultCommon resultCommon = null;
        try {
            RepairMan repairMan = repairManService.repairman_findById(id);
            resultCommon = ResultCommon.success(ResultEnum.SUCCESS,repairMan);
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/repairman_update")
    public ResultCommon repairman_update(RepairMan repairMan){
        ResultCommon resultCommon = null;
        try {
            int count = repairManService.repairman_update(repairMan);
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

    @RequestMapping("/repairman_addphoto")
    @ResponseBody
    public ResultCommon repairman_addphoto(MultipartFile file, HttpServletRequest request){
        ResultCommon result = null;
        try {
            String[] split = file.getOriginalFilename().split("\\.");
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + "." + split[1];
            System.out.println("服务器生成的新的文件名:" + newFileName);
            //获取服务器上传的真实路径
            String realPath = request.getServletContext().getRealPath("/images/photo") + "/" + newFileName;
            System.out.println("服务器上传路径是：" + realPath);
            //开始上传
            file.transferTo(new File(realPath));
            result = ResultCommon.success(ResultEnum.SUCCESS,newFileName);
        } catch (IOException e) {
            System.out.println(e.toString());
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/repairman_add")
    public ResultCommon repairman_add(RepairMan repairMan){
        ResultCommon resultCommon = null;
        try {
            int count = repairManService.repairman_add(repairMan);
            if (count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @GetMapping("/repairman_delete")
    public ResultCommon repairman_delete(Integer id){
        ResultCommon resultCommon = null;
        try {
            int count = repairManService.repairman_delete(id);
            if (count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/repairers_delete")
    public ResultCommon repairers_delete(String ids){
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = repairManService.repairers_delete(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }
}
