package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.AdminDao;
import com.pzhu.bysj.pojo.Admin;
import com.pzhu.bysj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }

    @Override
    public String findMail(Integer adminId) {
        return adminDao.findMail(adminId);
    }

    @Override
    public Admin AdminInfo(Integer adminId) {
        return adminDao.AdminInfo(adminId);
    }
}
