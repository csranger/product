package com.csranger.product.dao;

import com.csranger.product.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        Assert.assertTrue(productInfoList.size() > 0);

    }

    @Test
    public void findByProductIdIn() {
        List<String> productIdList = Arrays.asList("157875196366160062", "157876323366164068", "167232323366164068");
        List<ProductInfo> productInfoList = productInfoDao.findByProductIdIn(productIdList);
        Assert.assertTrue(productIdList.size() > 1);
        System.out.println(productInfoList.size());
        log.info("productInfoList.size() = " + productInfoList.size());
    }
}