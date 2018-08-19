package com.csranger.product.result;

import lombok.Getter;

/**
 * 代表异常信息
 */
@Getter
public class CodeMsg {

    private Integer code;

    private String msg;


    // 结果正常是 CodeMsg 对象
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    // 通用异常
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");






    // 私有构造器
    private CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // toString
    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
