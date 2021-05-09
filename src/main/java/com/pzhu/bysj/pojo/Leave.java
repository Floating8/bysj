package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    private Integer id;
    private String lreason;
    private User user;
    private String StuId;
    private String ltime;
    private String duration;
    private String status;
    private String del;
}
