
# 项目过程
## 初始化
0. 新建项目是需要添加 Eureka Discover 依赖
1. 在启动类上加上 @EnableDiscoveryClient 注解
2. 配置到 eureka： eureka.client.service-url.defaultZone=http://localhost:8080/eureka/
3. 添加 spring-boot-starter-web 依赖

## 商品服务： API GET /product/list
### sql 创建表 + 数据库相关依赖与配置
1. product_category 表
    ```
    CREATE TABLE `product_category` (
      `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
      `category_name` varchar(64) NOT NULL DEFAULT '' COMMENT '类目名字',
      `category_type` int(11) NOT NULL COMMENT '类目编号',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`category_id`),
      UNIQUE KEY `uqe_category_type` (`category_type`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='类目表';
    ```
2. product_info 表
    ```
    CREATE TABLE `product_info` (
      `product_id` varchar(32) NOT NULL DEFAULT '',
      `product_name` varchar(64) NOT NULL DEFAULT '' COMMENT '商品名称',
      `product_price` decimal(8,2) NOT NULL COMMENT '单价',
      `product_stock` int(11) NOT NULL COMMENT '库存',
      `product_description` varchar(64) DEFAULT '' COMMENT '描述',
      `product_icon` varchar(512) DEFAULT '' COMMENT '小图',
      `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态 0-正常 1-下架',
      `category_type` int(11) NOT NULL COMMENT '类目编号',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`product_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';
    ``` 
3. mysql 和 spring-jpa 依赖
4. 表对应的 model 层对象，使用 lombok 依赖并安装 lombok 插件自动生成 setter getter 方法
    - 只需在响应类上加上 @Data 方法

### dao 层 和 controller 层
1. ProductInfo 对应的 dao 和 service 类           ProductInfoDao         ProductInfoService
2. ProductCategory 对应的 dao 和 service 类       ProductCategoryDao     ProductCategoryService
3. 对 service 类中的 方法 进行单元测试
4. ProductController 中 list 方法 处理  GET /product/list 请求，返回 Result<List<ProductVO>> 





