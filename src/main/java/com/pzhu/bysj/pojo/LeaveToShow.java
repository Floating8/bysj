package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveToShow {
    private Integer id;
    private String lreason;
    private String ltime;
    private String duration;
    private String status;
    private String stuName;
}
