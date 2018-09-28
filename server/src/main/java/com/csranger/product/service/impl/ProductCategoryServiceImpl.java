package com.csranger.product.service.impl;

import com.csranger.product.dao.ProductCategoryDao;
import com.csranger.product.model.ProductCategory;
import com.csranger.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;


    /**
     * 查询指定 类目编号 的类目：在 product_category 表中根据 category_type 查找
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(categoryTypeList);
        return productCategoryList;
    }
}
