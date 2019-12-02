package com.fh.controller;

import com.fh.service.IProductService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productSearch")
public class ProductSearchController {

    @Autowired
    private IProductService productService;

    @GetMapping("/{productId}")
    public ResponseServer getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }
}
