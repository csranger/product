package com.csranger.product.service;

import com.csranger.product.model.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询所有在架商品：在 product_info 表中根据 product_status (商品状态 0-正常 1-下架) 查找所以正常商品
     * 函数名中的 up 暗示在架的商品
     */
    List<ProductInfo> findUpAll();

}
