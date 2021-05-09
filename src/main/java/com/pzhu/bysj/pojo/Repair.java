package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {
    private Integer id;
    private String repairNo;
    private String createTime;
    private String endTime;
    private String content;
    private Integer state;  // 0 未受理  1 受理中  2 受理完成
    private Integer repairedId;
    private User user;
    private String money;
    private Integer repairManId;
    private RepairMan repairMan;
}
