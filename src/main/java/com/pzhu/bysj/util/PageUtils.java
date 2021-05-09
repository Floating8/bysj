package com.pzhu.bysj.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: HouseFun
 * @BelongsPackage: com.housefun.commons
 * @CreateTime: 2020-09-04 14:41
 * @Description: 通用的分页工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUtils {

    private Long pageIndex; //当前页码
    private Long pageSize; //页面大小
    private Long totalCount; //总条数
    private Long pageCount; //总页数 后期计算出来

    private Object datas;  //分页数的数据

    private Long numberStart;//开始循环的序号
    private Long numberEnd;//结束循环的序号

    //存储序号
    private List<Long> numbers = new ArrayList<>();

    public PageUtils(Long pageIndex, Long pageSize, Long totalCount, Object datas) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.datas = datas;
        this.pageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);

        //给开始和结束的序号赋值
        if (this.pageCount <= 10) {
            this.numberStart = 1L;
            this.numberEnd = pageCount;
        } else {
            this.numberStart = pageIndex - 4;
            this.numberEnd = pageIndex + 5;
            if (numberStart < 1) {
                this.numberStart = 1L;
                this.numberEnd = 10L;
            } else if (numberEnd > pageCount) {
                this.numberEnd = pageCount;
                this.numberStart = pageCount - 9;
            }
        }

        for (long i=numberStart;i<=numberEnd;i++) {
            numbers.add(i);
        }
    }
}
