package com.fh.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductBean implements Serializable {

    private Integer id;

    private Integer brandId;

    private String name;

    private String subtitle;

    private String mainImg;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;


    //展示品牌名称使用
    private String brandName;
    //展示商品类型名称
    private String categroyName;
    //获取商品类型ID
    private String categroyIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategroyName() {
        return categroyName;
    }

    public void setCategroyName(String categroyName) {
        this.categroyName = categroyName;
    }

    public String getCategroyIds() {
        return categroyIds;
    }

    public void setCategroyIds(String categroyIds) {
        this.categroyIds = categroyIds;
    }
}
