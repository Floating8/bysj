package com.pzhu.bysj.service;

import com.pzhu.bysj.pojo.Admin;

public interface AdminService {
    Admin login(Admin admin);

    String findMail(Integer adminId);

    Admin AdminInfo(Integer i);
}
