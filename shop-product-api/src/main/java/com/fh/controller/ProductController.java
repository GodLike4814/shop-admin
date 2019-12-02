package com.fh.controller;


import com.fh.bean.ProductBean;
import com.fh.bean.ProductParamter;
import com.fh.service.IProductService;
import com.fh.utils.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081")
public class ProductController {

    @Autowired
    IProductService productService;
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping
    public PageBean<ProductBean> queryProductList(PageBean page, ProductParamter paramter){

        page=productService.queryProductPageList(page,paramter);
        return page;
    }


}

