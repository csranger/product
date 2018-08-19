package com.csranger.product.dao;

import com.csranger.product.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        Assert.assertTrue(productInfoList.size() > 0);

    }
}