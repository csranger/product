package com.csranger.product.enums;

import lombok.Getter;

/**
 * productStatus 字段两个值使用 枚举类 表示而已
 */
@Getter          // lombok 依赖实现 getter 方法
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOWN(1, "下架");


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
