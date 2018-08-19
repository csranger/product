package com.csranger.product.dao;

import com.csranger.product.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository<T, ID>
 * T    the domain type the repository manages                       ： Dao 层所管理的 model 类型
 * ID   the type of the id of the entity the repository manages      ： 此 model 类型的主键类型
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {      // extends JpaRepository 就不需要加上 @Repository 注解了

    /**
     * 查找：在 product_info 表中根据 product_status (商品状态 0-正常 1-下架) 查找商品
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
