package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Qfee {
    private Integer id;
    private String qfno;
    private String qftime;
    private String jftime;
    private String money;
    private String remark;
    private String usecount;
    private Dorm dorm;
    private Integer status;
}
