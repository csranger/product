package com.csranger.product.service.impl;

import com.csranger.product.dao.ProductInfoDao;
import com.csranger.product.enums.ProductStatusEnum;
import com.csranger.product.model.ProductInfo;
import com.csranger.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;


    /**
     * 查询所有在架商品：根据 ProductInfo 的 productStatus 字段
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());     // 应该传入参数 0，不好使用枚举类
    }
}
