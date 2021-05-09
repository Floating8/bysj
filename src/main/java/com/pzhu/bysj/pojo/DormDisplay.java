package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormDisplay {
    private Integer id;
    private String buildid;
    private String dname;//宿舍号
    private String chief;//室长
    private String owner;//有哪些人
    private String admin;
    private String own;//属于哪一栋
}
