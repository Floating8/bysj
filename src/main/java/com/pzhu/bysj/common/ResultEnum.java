package com.pzhu.bysj.common;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "请求成功"),
    REPAIR_SUCCESS(200,"申请维修成功"),
    FAIL(6000, "请求失败"),
    NO_LOGIN(6000, "未登录"),
    FAIL_CODE_NOT_TRUE(6001,"验证码错误"),
    REPAIR_FAIL(6002,"申请维修失败"),
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
