package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackLate {
    private Integer id;
    private Integer stuid;
    private String name;
    private String dname;
    private String backtime;
}
