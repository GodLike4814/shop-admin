package com.fh.service;


import com.fh.bean.ProductBean;
import com.fh.bean.ProductParamter;
import com.fh.utils.page.PageBean;
import com.fh.utils.response.ResponseServer;

public interface IProductService {
    PageBean<ProductBean> queryProductPageList(PageBean<ProductBean> page, ProductParamter paramter);

    ResponseServer getProductById(Integer productId);
}
