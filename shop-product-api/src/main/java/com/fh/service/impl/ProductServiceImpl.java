package com.fh.service.impl;


import com.fh.bean.ProductBean;
import com.fh.bean.ProductParamter;
import com.fh.dao.IProductDao;
import com.fh.service.IProductService;

import com.fh.utils.page.PageBean;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public PageBean<ProductBean> queryProductPageList(PageBean<ProductBean> page, ProductParamter paramter) {
        Long count=productDao.queryProductCount(paramter);
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        //查询分页数据
        List<ProductBean> productList=productDao.queryList(page,paramter);
        page.setData(productList);
        return page;
    }

    @Override
    public ResponseServer getProductById(Integer productId) {
        ProductBean product=productDao.getProductById(productId);
        return ResponseServer.success(product);
    }

}
