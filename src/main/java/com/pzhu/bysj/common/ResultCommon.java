package com.pzhu.bysj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//success(data){if(data.code==200)}
public class ResultCommon<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private ResultCommon(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private ResultCommon(ResultEnum resultEnum, T data) {
        this(resultEnum);
        this.data = data;
    }

    //成功的方法
    public static <T> ResultCommon<T> success() {
        return new ResultCommon<>(ResultEnum.SUCCESS);
    }

    //成功的方法
    public static <T> ResultCommon<T> success(T data) {
        return new ResultCommon<>(ResultEnum.SUCCESS, data);
    }
    public static <T> ResultCommon<T> success(ResultEnum resultEnum) {
        return new ResultCommon<>(resultEnum);
    }
    //成功的方法
    public static <T> ResultCommon<T> success(ResultEnum resultEnum, T data) {
        ResultCommon<T> success = success(resultEnum);
        success.setData(data);
        return success;
    }


    //失败的方法
    public static <T> ResultCommon<T> fail() {
        return new ResultCommon<>(ResultEnum.FAIL);
    }

    public static <T> ResultCommon<T> fail(ResultEnum resultEnum) {
        return new ResultCommon<>(resultEnum);
    }

    //失败的方法
    public static <T> ResultCommon<T> fail(T data) {
        return new ResultCommon<>(ResultEnum.FAIL, data);
    }

    //失败的方法
    public static <T> ResultCommon<T> fail(ResultEnum resultEnum, T data) {
        ResultCommon<T> fail = fail(resultEnum);
        fail.setData(data);
        return fail;
    }

    public boolean isSuccess() {
        return this.code == ResultEnum.SUCCESS.getCode();
    }
}
