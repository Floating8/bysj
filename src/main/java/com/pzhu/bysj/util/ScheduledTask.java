package com.pzhu.bysj.util;

import com.pzhu.bysj.pojo.Leave;
import com.pzhu.bysj.service.AdminService;
import com.pzhu.bysj.service.DormService;
import com.pzhu.bysj.service.LeaveService;
import com.pzhu.bysj.service.UserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 构建执行定时任务
 * TODO
 */
@Component
public class ScheduledTask {

//    @Autowired
//    ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    LeaveService leaveService;

    @Autowired
    UserService userService;

    @Autowired
    DormService dormService;

    @Autowired
    AdminService adminService;

    @Autowired
    JavaMailSender javaMailSender;

    public static  int i = 0;

    @Scheduled(cron = "0 30 8/1 * * ?")
    //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public void testFixDelay() throws MessagingException {
        List<Leave> leaves = leaveService.findUnprocessed();
        ArrayList<String> mailList = new ArrayList<>();

        for(int i = 0;i < leaves.size(); i ++){
            Leave leave = leaves.get(i);
            String stuId = leave.getStuId();
            String dormId = userService.findDorm(stuId);
            Integer buildId = dormService.findBuildId(dormId);
            Integer adminId = dormService.findAdminId(buildId);
            String mail = adminService.findMail(adminId);
            mailList.add(mail);
        }

        if (leaves!=null){
            for (int j = 0; j < mailList.size(); j++) {
                System.out.println(1111);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("775395428@qq.com");
                simpleMailMessage.setTo(mailList.get(j));
                simpleMailMessage.setSubject("工作提醒");
                simpleMailMessage.setText("请注意及时查看工作消息");
                javaMailSender.send(simpleMailMessage);
            }

        }
    }

}