package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairMan {
    private Integer id;
    private String no;
    private String name;
    private String sex;
    private String password;
    private String photo;
    private String phone;
}
