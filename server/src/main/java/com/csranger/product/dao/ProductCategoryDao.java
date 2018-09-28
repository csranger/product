package com.csranger.product.dao;

import com.csranger.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    /**
     * 查询指定 类目编号 的类目：在 product_category 表中根据 category_type 查找
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
