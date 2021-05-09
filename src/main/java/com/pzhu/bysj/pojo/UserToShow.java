package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToShow {
    private User user;
    private String qinshi;
    private Integer id;
    private String name;
    private Integer Stu_ID;
    private String username;//账号
    private String password;
    private String nation;
    private Integer ryouid;//宿舍外键
    private String birth;
    private String image;
    private String sex;
    private String dorm;
}
