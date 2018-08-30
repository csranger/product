package com.csranger.product.service;

import com.csranger.product.dto.CartDTO;
import com.csranger.product.ProductApplicationTests;
import com.csranger.product.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component   // 这种继承 ProductApplicationTests 方法也可，不再每次加上 两个注解：@RunWith(SpringRunner.class) @SpringBootTest
@Slf4j
public class ProductInfoServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertTrue(productInfoList.size() > 0);
    }

    @Test
    public void findList() {
        List<String> productIdList = Arrays.asList("157875196366160062", "157876323366164068", "167232323366164068");
        List<ProductInfo> productInfoList = productInfoService.findList(productIdList);
        Assert.assertTrue(productIdList.size() == 3);
        log.info(productInfoList.toString());
    }

    @Test
    public void decreaseStock() {
        CartDTO cartDTO = new CartDTO("157876323366164068", 5);
        productInfoService.decreaseStock(Arrays.asList(cartDTO));
    }
}