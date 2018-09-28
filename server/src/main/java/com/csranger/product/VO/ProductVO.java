package com.csranger.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * /product/list 静态页面所需要的 json 数据封装到这个类
 */
@Data
public class ProductVO {

    @JsonProperty("name")           // 作用是这个属性输出json是属性名为name，这里使用categoryName作为属性是为了清晰表达字段意思,和model里属性命名一致
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;

}
