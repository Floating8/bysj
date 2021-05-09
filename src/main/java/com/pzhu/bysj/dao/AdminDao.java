package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.Admin;
import org.mapstruct.Mapper;

@Mapper
public interface AdminDao {
    Admin login(Admin admin);

    String findMail(Integer adminId);

    Admin AdminInfo(Integer adminId);

}
