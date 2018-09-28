package com.csranger.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目对象
 */
@Data
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue                        // 此主键自增
    private Integer categoryId;
    private String categoryName;           // 类目名字
    private Integer categoryType;          // 类目编号
    private Date createTime;
    private Date updateTime;
}
