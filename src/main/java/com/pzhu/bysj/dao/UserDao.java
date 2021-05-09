package com.pzhu.bysj.dao;

import com.pzhu.bysj.pojo.Count;
import com.pzhu.bysj.pojo.User;
import org.mapstruct.Mapper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserDao {
    User Login(User user);

    User getUserInfo(Integer id);

    User getUserNaka(Integer stuId);

    long totalCount();

    List<User> findUsers(HashMap<String, Object> map);

    int deleteUser(Integer id);

    int deleteUsers(String[] split);

    User userFindById(Integer id);

    int addUser(User user);

    int updateUser(User user);

    long totalCount2(Integer ryouid);

    List<User> findUsers2(HashMap<String, Object> map);

    List<Count> findCount();

    List<User> findUsersExcel(Long ryouid);

    List<User> findUsersExcel2();

    int insertBanch(ArrayList<User> users);

    String findDorm(String stuId);

    long totalCount3(String input);

    List<User> findStu(HashMap<String, Object> map);

    int user_updateStuInfo(String password, String sex,Integer id);
}
