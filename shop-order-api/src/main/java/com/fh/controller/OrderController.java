package com.fh.controller;

import com.fh.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("orders")
@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders="NOLOGIN")
public class OrderController {

    @Autowired
    private IOrderService orderService;



}
