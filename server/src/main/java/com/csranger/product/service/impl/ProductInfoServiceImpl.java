package com.csranger.product.service.impl;

import com.csranger.product.common.DecreaseStockInput;
import com.csranger.product.dao.ProductInfoDao;
import com.csranger.product.enums.ProductStatusEnum;
import com.csranger.product.exception.ProductException;
import com.csranger.product.model.ProductInfo;
import com.csranger.product.result.CodeMsg;
import com.csranger.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    /**
     * 查找：根据一组商品id productId 的list查询商品信息 ProductInfo
     */
    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoDao.findByProductIdIn(productIdList);
    }

    /**
     * 更新：扣库存
     * 减少商品的库存数量
     */
    @Override
    @Transactional     // 因为购物车一次会噶偶买多个商品，如果其中一个商品库存不够，则整个购买的减库存操作均需要撤销
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            // 先查下商品是否存在？根据 productId 查询商品
            Optional<ProductInfo> productInfoOptional = productInfoDao.findById(decreaseStockInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(CodeMsg.PRODUCT_NOT_EXIST);
            }
            // 如果存在商品库存够不够？
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(CodeMsg.PRODUCT_STOCK_ERROR);
            }
            // 减库存：更新商品的库存数量
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

}
