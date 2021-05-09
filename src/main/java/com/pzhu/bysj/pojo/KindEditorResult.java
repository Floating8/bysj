package com.pzhu.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: HouseFun
 * @BelongsPackage: com.housefun.bean
 * @CreateTime: 2020-09-09 15:39
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KindEditorResult {

    private Integer error;
    private String url;
    private String message;
}
