package com.pzhu.bysj.service.impl;

import com.pzhu.bysj.dao.UserDao;
import com.pzhu.bysj.pojo.Count;
import com.pzhu.bysj.pojo.User;
import com.pzhu.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.Login(user);
    }

    @Override
    public User getUserInfo(Integer id) {
        return userDao.getUserInfo(id);
    }

    @Override
    public User getUserNaka(Integer stuId) {
        return userDao.getUserNaka(stuId);
    }

    @Override
    public long totalCount() {
        return userDao.totalCount();
    }

    @Override
    public List<User> findUsers(HashMap<String, Object> map) {
        return userDao.findUsers(map);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int deleteUsers(String[] split) {
        return userDao.deleteUsers(split);
    }

    @Override
    public User userFindById(Integer id) {
        return userDao.userFindById(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public long totalCount2(Integer ryouid) {
        return userDao.totalCount2(ryouid);
    }

    @Override
    public List<User> findUsers2(HashMap<String, Object> map) {
        return userDao.findUsers2(map);
    }

    @Override
    public List<Count> findCount() {
        return userDao.findCount();
    }

    @Override
    public List<User> findUsersExcel(Long ryouid) {
        return userDao.findUsersExcel(ryouid);
    }

    @Override
    public List<User> findUsersExcel2() {
        return userDao.findUsersExcel2();
    }

    @Override
    public int insertBanch(ArrayList<User> users) {
        return userDao.insertBanch(users);
    }

    @Override
    public String findDorm(String stuId) {
        return userDao.findDorm(stuId);
    }

    @Override
    public long totalCount3(String input) {
        return userDao.totalCount3(input);
    }

    @Override
    public List<User> findStu(HashMap<String, Object> map) {
        return userDao.findStu(map);
    }

    @Override
    public int user_updateStuInfo(String password, String sex,Integer id) {
        return userDao.user_updateStuInfo(password,sex,id);
    }
}
