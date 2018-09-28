
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
### 减库存实现

## 改造成多模块 
### 商品服务供订单服务调用存在的问题
1. 直接暴露了与数据库对应的实体类
    - ProductController 类中的 listForOrder 方法直接返回 List<ProductInfo>
    - ProductInfo 与数据库相对应，这不安全
2. 订单服务与商品服务有许多重复类，比如CartDTO，ProductInfo 等类
3. ProductClient 里写了商品服务的一些接口，开发订单服务的可能不熟悉，放在订单服务不合适，应该放在商品服务以供调用，因为这两组服务可能有不同团队负责

### 将订单模块和商品模块都改造成多模块解决上述问题
1. 商品服务：
    - product-server 所有业务逻辑                     依赖 product-common
    - product-client 对外暴露的接口，可供订单服务调用    依赖 product-common
    - product-common 公用对象，可被外部服务调用，也被内部服务使用
2. dependencyManagement和dependencies区别：
    - dependencies:自动引入声明在dependencies里的所有依赖，并默认被所有的子项目继承。如果项目中不写依赖项，则会从父项目
   继承（属性全部继承）声明在父项目dependencies里的依赖项。
    - dependencyManagement里只是声明依赖，并不实现引入，因此子项目需要显示的声明需要的依赖。如果不在子项目中声明依赖，
   是不会从父项目中继承的；只有在子项目中写了该依赖项，并且没有指定具体版本，才会从父项目中继承该项，并且version和scope都读取
   自父pom;如果子项目中指定了版本号，那么会使用子项目中指定的jar版本。同时dependencyManagement让子项目引用依赖，而不用显示的列
   出版本号。Maven会沿着父子层次向上走，直到找到一个拥有dependencyManagement元素的项目，然后它就会使用在这个
   dependencyManagement元素中指定的版本号,实现所有子项目使用的依赖项为同一版本。
    - dependencyManagement 中的 dependencies 并不影响项目的依赖项；而独立dependencies元素则影响项目的依赖项。只有当外
   层的dependencies元素中没有指明版本信息时，dependencyManagement 中的 dependencies 元素才起作用。一个是项目依赖，一个是maven
   项目多模块情况时作依赖管理控制的。
3. 将原先在 Order 服务里的 ProductClient 放在 Product 服务里
4. 因为 ProductClient 在 Product 服务里，所以需要将 product 生成 jar 安装到本地 maven 仓库以供调用
    - mvn -Dmaven.test.skip=true -U clean install







