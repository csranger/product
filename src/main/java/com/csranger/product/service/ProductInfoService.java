package com.csranger.product.service;

import com.csranger.product.dto.CartDTO;
import com.csranger.product.model.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询所有在架商品：在 product_info 表中根据 product_status (商品状态 0-正常 1-下架) 查找所以正常商品
     * 函数名中的 up 暗示在架的商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 查找：根据一组商品id productId 的list查询商品信息 ProductInfo
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 更新：扣库存
     * 减少商品的库存数量
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
