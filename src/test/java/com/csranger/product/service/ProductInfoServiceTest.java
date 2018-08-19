package com.csranger.product.service;

import com.csranger.product.ProductApplicationTests;
import com.csranger.product.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;


@Component   // 这种继承 ProductApplicationTests 方法也可，不再每次加上 两个注解：@RunWith(SpringRunner.class) @SpringBootTest
public class ProductInfoServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertTrue(productInfoList.size() > 0);
    }
}