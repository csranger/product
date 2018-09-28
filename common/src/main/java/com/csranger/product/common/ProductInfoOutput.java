package com.csranger.product.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 同 ProductInfo
 * 不直接使用 ProductInfo，因为 ProductInfo 和数据库关联类，防止暴露
 */
@Data
public class ProductInfoOutput {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;              // 商品状态 0-正常 1-下架
    private Integer categoryType;               // 类目编号 和 product_category 表关联
    private Date createTime;
    private Date updateTime;

}
