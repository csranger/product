package com.csranger.product.exception;

import com.csranger.product.result.CodeMsg;
import lombok.Getter;

/***
 * 商品模块异常
 * 5003XX
 */
@Getter
public class ProductException extends RuntimeException {

    private CodeMsg codeMsg;

    public ProductException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }
}
