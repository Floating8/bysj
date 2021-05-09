package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pzhu.bysj.pojo.Admin;

/**
 * @BelongsProject: HouseFun
 * @BelongsPackage: com.housefun.bean
 * @CreateTime: 2020-09-09 09:51
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private Integer id;
    private String title;
    private String content;

    public News(String title, String content, Admin admin) {
        this.title = title;
        this.content = content;
        this.admin = admin;
    }

    private String updatetime;
    private String createtime;
    private Integer hit;
    private Admin admin;
    private Integer del;
}
