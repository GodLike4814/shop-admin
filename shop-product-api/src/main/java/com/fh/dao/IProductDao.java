package com.fh.dao;


import com.fh.bean.ProductBean;
import com.fh.bean.ProductParamter;
import com.fh.utils.page.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface IProductDao {
    Long queryProductCount(@Param("paramter") ProductParamter paramter);

    List<ProductBean> queryList(@Param("page") PageBean<ProductBean> page, @Param("paramter") ProductParamter paramter);

    ProductBean getProductById(Integer productId);
}
