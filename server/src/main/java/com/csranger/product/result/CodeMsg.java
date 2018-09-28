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

    // 通用异常 5001XX
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    // 商品模块异常 5003XX
    public static CodeMsg PRODUCT_NOT_EXIST = new CodeMsg(500300, "商品不存在");
    public static CodeMsg PRODUCT_STOCK_ERROR = new CodeMsg(500301, "商品库存不够");




    // 构造器，未知异常使用 500000
    public CodeMsg(Integer code, String msg) {
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
