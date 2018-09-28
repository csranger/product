package com.csranger.product.service;

import com.csranger.product.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询指定 类目编号 的类目：在 product_category 表中根据 category_type 查找
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
