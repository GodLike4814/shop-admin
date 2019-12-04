package com.fh.controller;

import com.fh.login.aop.LoginAnnotation;
import com.fh.service.IOrderService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("orders")
@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders="NOLOGIN")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PutMapping("/createOrder")
    @LoginAnnotation
    public ResponseServer createOrder(HttpServletRequest request,Integer addressId){
        String phone = (String) request.getAttribute("phone");
        return orderService.createOrder(addressId,phone);
    }

}
