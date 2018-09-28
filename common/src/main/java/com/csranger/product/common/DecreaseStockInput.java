package com.csranger.product.common;

import lombok.Data;

/**
 * 同 CartDTO：CartDTO属于订单服务的
 * 这里使用相同字段的 DecreaseStockInput 代替，表现商品服务的独立性
 */
@Data
public class DecreaseStockInput {
    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public DecreaseStockInput() {
    }
}
