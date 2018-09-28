package com.csranger.product.client;

import com.csranger.product.common.DecreaseStockInput;
import com.csranger.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 使用 Feign 进行服务间通讯
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping(value = "/msg")                             // 访问 product 应用下的 msg 接口
    String productMsg();

    /**
     * 访问 product 应用下的 /product/listForOrder 接口
     * 查询商品信息
     */
    @PostMapping(value = "/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);  // @RequestBody 视为处理传过来的参数是一个json字符串，要求请求必须是POST

    /**
     * 访问 product 应用下的 /product/decreaseStock 接口
     * 减库存
     * DecreaseStockInput 就是 CartDTO
     */
    @PostMapping(value = "/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);

}
