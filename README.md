
# 项目过程
## 初始化
0. 新建项目是需要添加 Eureka Discover 依赖
1. 在启动类上加上 @EnableDiscoveryClient 注解
2. 配置到 eureka： eureka.client.service-url.defaultZone=http://localhost:8080/eureka/
3. 添加 spring-boot-starter-web 依赖

## 商品服务
### API 
1. GET /product/list
2. 返回
    ```
    {
        "code": 0,
        "msg": "success",
        "data": [
            {
                "name": "热榜",
                "type": 1,
                "foods": [
                    {
                        "id": "157875196366160062",
                        "name": "皮蛋粥",
                        "price": 0.01,
                        "description": "好吃的皮蛋粥",
                        "icon": "//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg"
                    },
                    {
                        "id": "167232323366164068",
                        "name": "蜜汁鸡翅",
                        "price": 0.02,
                        "description": "好吃",
                        "icon": "//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg"
                    }
                ]
            }
        ]
    }
    ```
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
5. 可以启动多个 Product 应用，在 Edit Configuration 中配置，可以将第二个 Product 应用端口号修改为 -Dserver.port=9080

### dao 层 和 controller 层
1. ProductInfo 对应的 dao 和 service 类           ProductInfoDao         ProductInfoService
2. ProductCategory 对应的 dao 和 service 类       ProductCategoryDao     ProductCategoryService
3. 对 service 类中的 方法 进行单元测试
4. ProductController 中 list 方法 处理  GET /product/list 请求，返回 Result<List<ProductVO>> 

## 订单服务(新建一个项目 order)
### API 
1. POST /order/create
2. 参数 
    ```
    name:       张三   
    phone:      "1782371231"
    address:    "XXXX"
    openid:     "....."
    items:      [{
        productId:          "18723423",
        productQuantity:    2
    }]
    ```
3. 返回
    ```
    {
       "code":  0,
       "msg": success,
       "data": {
           orderId: "12383242"
       }
    }
    ```
### sql 创建表
1. order_detail 表
    ```
    CREATE TABLE `order_detail` (
      `detail_id` varchar(32) NOT NULL DEFAULT '',
      `order_id` varchar(32) NOT NULL DEFAULT '',
      `product_id` varchar(32) NOT NULL DEFAULT '',
      `product_name` varchar(64) NOT NULL DEFAULT '' COMMENT '商品名称',
      `product_price` decimal(8,2) NOT NULL COMMENT '当前价格，单位分',
      `product_quantity` int(11) NOT NULL COMMENT '数量',
      `product_icon` varchar(512) DEFAULT '' COMMENT '小图',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`detail_id`),
      KEY `idx_order_id` (`order_id`),
      CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_master` (`order_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';
    ```
2. order_master 表
    ```
    CREATE TABLE `order_master` (
      `order_id` varchar(32) NOT NULL DEFAULT '',
      `buyer_name` varchar(32) NOT NULL DEFAULT '' COMMENT '买家名字',
      `buyer_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '买家电话',
      `buyer_address` varchar(128) NOT NULL DEFAULT '' COMMENT '买家地址',
      `buyer_openid` varchar(64) NOT NULL DEFAULT '' COMMENT '买家微信openid',
      `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
      `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认为新订单',
      `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认未支付',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`order_id`),
      KEY `idx_buyer_openid` (`buyer_openid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
    ``` 
    
## 供订单服务调用的商品服务
### 创建订单前先查询商品信息实现





