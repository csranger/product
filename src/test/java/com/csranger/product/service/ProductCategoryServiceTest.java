package com.csranger.product.service;

import com.csranger.product.ProductApplicationTests;
import com.csranger.product.model.ProductCategory;
import com.csranger.product.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductCategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(productCategoryList.size() > 0);
    }
}