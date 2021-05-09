package com.pzhu.bysj.controller;

import com.pzhu.bysj.common.ResultCommon;
import com.pzhu.bysj.common.ResultEnum;
import com.pzhu.bysj.pojo.*;
import com.pzhu.bysj.service.DormService;
import com.pzhu.bysj.service.QfeeService;
import com.pzhu.bysj.service.UserService;
import com.pzhu.bysj.util.PageUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //学生登录
    @PostMapping("/user_login")
    public ResultCommon userLogin(User user, String code, HttpSession session) {
        ResultCommon result = null;
        User login = userService.login(user);
        String safeCode = (String) session.getAttribute("safeCode");
        if (!safeCode.equalsIgnoreCase(code)) {
            result = ResultCommon.fail(ResultEnum.FAIL_CODE_NOT_TRUE);
        } else {
            if (login != null) {
                session.setAttribute("login", login);
                result = ResultCommon.success(ResultEnum.SUCCESS);
            } else {
                result = ResultCommon.fail(ResultEnum.FAIL);
            }
        }
        return result;
    }

    //学生注销
    @GetMapping("/user_Logout")
    public ResultCommon userLogout(HttpSession session) {
        ResultCommon result = null;
        try {
            session.removeAttribute("login");
        } catch (Exception e) {
            result = ResultCommon.fail(ResultEnum.FAIL);
            return result;
        }
        result = ResultCommon.success(ResultEnum.SUCCESS);
        return result;
    }

    //获取登录信息
    @GetMapping("/user_getLoginUser")
    public ResultCommon getLoginUser(HttpSession session) {
        ResultCommon result = null;
        User login = (User) session.getAttribute("login");
        result = ResultCommon.success(login);
        return result;
    }

    @GetMapping("/user_info")
    public ResultCommon user_info(Integer id) {
        ResultCommon result = null;
        User user = userService.getUserInfo(id);
        Dorm dormInfo = dormService.findDormInfo(user.getRyouid());
        String dname = dormInfo.getDname();
        String own = dormInfo.getOwn();
        UserToShow userToShow = new UserToShow();
        userToShow.setUser(user);
        userToShow.setQinshi(own+" "+dname);
        if (user != null) {
            result = ResultCommon.success(userToShow);
        } else {
            result = ResultCommon.fail();
        }
        return result;
    }

    @GetMapping("/user_naka")
    public User user_nake(Integer StuId) {
        return userService.getUserNaka(StuId);
    }

    @GetMapping("/user_list")
    public PageUtils user_list(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "8") Long pageSize) {
        long totalCount = userService.totalCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<User> users = userService.findUsers(map);
        //Long pageIndex, Long pageSize, Long totalCount, Object datas
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize, totalCount, users);
        return pageUtils;
    }

    @PostMapping("/delete_user")
    public ResultCommon delete_user(Integer id) {
        ResultCommon resultCommon = null;
        try {
            int count = userService.deleteUser(id);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @PostMapping("/deleteUsers")
    public ResultCommon deleteUsers(String ids) {
        String[] split = ids.split(",");
        ResultCommon result = null;
        int count = userService.deleteUsers(split);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @GetMapping("/user_findById")
    public ResultCommon user_findById(Integer id) {
        ResultCommon resultCommon = null;
        try {
            User user = userService.userFindById(id);
            resultCommon = ResultCommon.success(ResultEnum.SUCCESS, user);
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @RequestMapping("/user_addmap")
    @ResponseBody
    public ResultCommon user_addmap(MultipartFile file, HttpServletRequest request) {
        ResultCommon result = null;
        try {
            String[] split = file.getOriginalFilename().split("\\.");
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + "." + split[1];
            System.out.println("服务器生成的新的文件名:" + newFileName);
            //获取服务器上传的真实路径
            String realPath = request.getServletContext().getRealPath("/images/users") + "/" + newFileName;
            System.out.println("服务器上传路径是：" + realPath);
            //开始上传
            file.transferTo(new File(realPath));
            result = ResultCommon.success(ResultEnum.SUCCESS, newFileName);
        } catch (IOException e) {
            System.out.println(e.toString());
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @PostMapping("/User_add")
    public ResultCommon User_add(User user, Dorm dorm) {
        ResultCommon resultCommon = null;
        user.setDorm(dorm);
        try {
            int count = userService.addUser(user);
            if (count > 0) {
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }

    @Autowired
    DormService dormService;

    @PostMapping("/user_update")
    public ResultCommon user_update(User user, Dorm dorm, HttpServletRequest request,String own1) {
        ResultCommon result = null;
        dorm.setOwn(own1);
        user.setDorm(dorm);
        Integer id = user.getId();
        User oldUser = userService.userFindById(id);
        System.out.println("oldHouser.getHousemap()：" + oldUser.getImage());
        //String housemap = houser.getHousemap();
        if ("".equals(user.getImage())) {
            //表示没有更新户型图，数据库保持原来的图片不更新
            user.setImage(oldUser.getImage());
            System.out.println("没有更新图片");
        } else {
            String oldPath = request.getServletContext().getRealPath("/images/houses") + "\\" + oldUser.getImage();
            File file = new File(oldPath);
            if (file.exists()) {
                file.delete();
            }
            System.out.println("更新图片,删除了老的图片:" + oldPath);
        }
        int count = userService.updateUser(user);
//        int cont  = dormService.updateDorm(dorm);
        if (count > 0) {
            result = ResultCommon.success(ResultEnum.SUCCESS);
        } else {
            result = ResultCommon.fail(ResultEnum.FAIL);
        }
        return result;
    }

    @Autowired
    QfeeService qfeeService;

    @PostMapping("/user_qfee")
    public List<Qfee> houser_qfee(HttpSession session) {
        User loginUser = (User) session.getAttribute("login");

        return qfeeService.findByDormId(loginUser.getRyouid());
    }

    @GetMapping("/user_find_list")
    public PageUtils user_find_list(@RequestParam(defaultValue = "1") Long pageIndex,
                                    @RequestParam(defaultValue = "8") Long pageSize, Integer ryouid) {
        PageUtils pageUtils = null;
        if (ryouid != -1) {
            long totalCount = userService.totalCount2(ryouid);
            HashMap<String, Object> map = new HashMap<>();
            map.put("pageStart", (pageIndex - 1) * pageSize);
            map.put("pageSize", pageSize);
            map.put("ryouid", ryouid);
            List<User> users = userService.findUsers2(map);
            //Long pageIndex, Long pageSize, Long totalCount, Object datas
            pageUtils = new PageUtils(pageIndex, pageSize, totalCount, users);
        } else {
            pageUtils = user_list(pageIndex, pageSize);
        }

        return pageUtils;
    }

    @GetMapping("/findStu")
    public PageUtils findStu(@RequestParam(defaultValue = "1") Long pageIndex,
                                    @RequestParam(defaultValue = "8") Long pageSize, String input){
        PageUtils pageUtils = null;
        if (input != null) {
            long totalCount = userService.totalCount3(input);
            HashMap<String, Object> map = new HashMap<>();
            map.put("pageStart", (pageIndex - 1) * pageSize);
            map.put("pageSize", pageSize);
            map.put("input",input);
            List<User> users = userService.findStu(map);
            //Long pageIndex, Long pageSize, Long totalCount, Object datas
            pageUtils = new PageUtils(pageIndex, pageSize, totalCount, users);
            return pageUtils;
        } else {
            pageUtils = user_list(pageIndex, pageSize);
        }

        return pageUtils;
    }

    @RequestMapping("/findCount")
    @ResponseBody
    public List<Count> findCount() {
        List<Count> count = userService.findCount();
        return count;
    }

    @RequestMapping("/user_exportExcel")
    public void houser_exportExcel(Long ryouid, HttpServletResponse response) throws Exception {
        List<User> UsersExcel = null;
        if (ryouid!=-1) {
            //查询得到的数据
            UsersExcel = userService.findUsersExcel(ryouid);
        }else {
            UsersExcel = userService.findUsersExcel2();
        }
        //在内存中创建一个工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //在工作簿中创建一个sheet
        HSSFSheet sheet = hssfWorkbook.createSheet("业主信息");
        //给第一行设置标题
        String[] title = {"学生编号", "学生姓名", "学生学号", "学生生日", "学生性别", "学生民族","学生宿舍","学生账号","学生密码"};
        //表示这是第一行
        int index = 0;
        HSSFRow row = sheet.createRow(index);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i = 0; i < UsersExcel.size(); i++) {
            //依次给每行赋值
            User user = UsersExcel.get(i);
            row = sheet.createRow(++index);
            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(user.getId());
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(user.getName());
            HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(user.getStu_ID());
            HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue(user.getBirth());
            HSSFCell cell5 = row.createCell(4);
            cell5.setCellValue(user.getSex());
            HSSFCell cell6 = row.createCell(5);
            cell6.setCellValue(user.getNation());
            HSSFCell cell7 = row.createCell(6);
            cell7.setCellValue(user.getDorm().getDname());
            HSSFCell cell8 = row.createCell(7);
            cell8.setCellValue(user.getUsername());
            HSSFCell cell9 = row.createCell(8);
            cell9.setCellValue(user.getUsername());
        }
        //执行文件下载
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = "学生信息表" + new SimpleDateFormat("YYYYMMDD").format(new Date()) + ".xls";
        //强制下载不需要打开
        response.setContentType("application/force-download");
        //文件编码
        response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
        hssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

    @RequestMapping("/user_importExcel")
    public void hosuer_importExcel(Integer DormId,MultipartFile file,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        ArrayList<User> users = new ArrayList<>();
        try {

            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet hssfSheet = workbook.getSheetAt(0);
//            XSSFWorkbook xssfSheets = new XSSFWorkbook(file.getInputStream());
//            XSSFSheet sheet = xssfSheets.getSheetAt(0);
            for (int i = 1; i <= hssfSheet.getLastRowNum(); i++) {
//                XSSFRow row = hssfSheet.getRow(i);
                Row row = hssfSheet.getRow(i);
                User h = new User();
                String value = "";
                for (int j = 0; j < 9; j++) {
//                    XSSFCell cell = row.getCell(j);
                    Cell cell = row.getCell(j);
                    if (cell==null){
                        value = "";
                    }else if (cell.getCellType()==XSSFCell.CELL_TYPE_BOOLEAN){
                        value = String.valueOf(cell.getBooleanCellValue());
                    }else if (cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                        value = cell.getNumericCellValue()+"";
                    }else {
                        value = cell.getStringCellValue();
                    }
                    if(j==1){
                        h.setName(value);
                    }else if(j==2){
                        h.setStu_ID(Integer.parseInt(value));
                    }else if(j==3){
                        h.setBirth(value);
                    }else if(j==4){
                        h.setSex(value);
                    }else if(j==5){
                        h.setNation(value);
                    }else if(j==7){
                        h.setUsername(value);
                    }else if(j==8){
                        h.setPassword(value);
                    }
                }
                Dorm dorm = new Dorm();
                dorm.setId(DormId);
                h.setDorm(dorm);
                users.add(h);

                int count = userService.insertBanch(users);
                if (count>0){
                    response.getWriter().write("<script>alert('数据导入成功');location.href='/admin/userlist.html';</script>");
                }else {
                    response.getWriter().write("<script>alert('数据导入失败');location.href='/admin/userlist.html';</script>");
                }
            }
        }catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/user_updateStuInfo")
    public ResultCommon user_updateStuInfo(UserToShow user,HttpServletRequest request){
        ResultCommon resultCommon = null;
        try {
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("login");
            int count = userService.user_updateStuInfo(user.getPassword(),user.getSex(),userLogin.getId());
            if(count>0){
                resultCommon = ResultCommon.success(ResultEnum.SUCCESS);
            }
        }catch (Exception e){
            resultCommon = ResultCommon.fail(ResultEnum.FAIL);
        }
        return resultCommon;
    }
}
