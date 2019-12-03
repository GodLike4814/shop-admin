package com.fh.controller;

import com.fh.login.aop.LoginAnnotation;
import com.fh.service.ICartService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/carts")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders="NOLOGIN")
public class CartController {

    @Autowired
    private ICartService cartService;
    @PostMapping
    @LoginAnnotation
    public ResponseServer addCart(Integer productId, HttpServletRequest request){
        String userPhone= (String) request.getAttribute("phone");
        return cartService.addCart(productId,userPhone);
    }

    @GetMapping("queryCartsChecked")
    @LoginAnnotation
    public ResponseServer queryCartsChecked(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        return cartService.queryCartsChecked(phone);
    }

    @GetMapping
    @LoginAnnotation
    public ResponseServer cartAll(){

        return ResponseServer.success();
    }
    @GetMapping("queryCartCount")
    public ResponseServer queryCartCount(HttpServletRequest request){
        String userPhone= (String) request.getAttribute("phone");
        Long count=cartService.queryCartCount(userPhone);
        System.out.println(count);
        return ResponseServer.success(count);
    }
}