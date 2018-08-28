package com.csranger.product.controller;

import com.csranger.product.VO.ProductInfoVO;
import com.csranger.product.VO.ProductVO;
import com.csranger.product.model.ProductCategory;
import com.csranger.product.model.ProductInfo;
import com.csranger.product.result.Result;
import com.csranger.product.service.ProductCategoryService;
import com.csranger.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表页：处理 GET /product/list 请求
     * /product/list 页面所需数据封装到 ProductVO 类
     * 将所有在架商品按照 类目 分类列出："好吃的"一类商品列表     "热榜"一类商品列表
     */
    @GetMapping(value = "/list")
    public Result<List<ProductVO>> list() {
        // 1. 查询所有在架商品(在 product_info 表中根据 product_status (商品状态 0-正常 1-下架) 查找所以正常商品)
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 2. 获取类目 type 列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 3. 查询指定 类目编号 的类目
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 4. 构造 GET /product/list 页面所需数据 ProductVO (使用数据库查到的 List<ProductInfo> 和 List<ProductCategory>)
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            // 4.1 ProductVO 的 CategoryType 和 CategoryName 属性
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            // 4.2 ProductVO 的 List<ProductInfoVO> 属性
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return Result.success(productVOList);
    }


    /**
     * 查询商品信息：获取商品列表(给订单服务用的)
     * 创建订单需要先根据 商品id 查询商品信息，订单服务传来的参数是：商品id的list
     * 使用 @RequestBody 修饰参数，则必须 @PostMapping
     */
    @PostMapping(value = "/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {  // @RequestBody 视为处理传过来的参数是一个json字符串，要求请求必须是POST
        return productInfoService.findList(productIdList);
    }

    /**
     * 扣库存(给订单服务用的)
     * 创建订单前需要先减少购买商品的库存
     */
}
