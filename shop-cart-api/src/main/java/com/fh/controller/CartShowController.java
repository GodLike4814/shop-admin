package com.fh.controller;

import com.fh.login.aop.LoginAnnotation;
import com.fh.service.ICartService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cartsShow")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders="NOLOGIN")
public class CartShowController {

    @Autowired
    private ICartService cartService;
    @GetMapping
    @LoginAnnotation
    public ResponseServer cartAll(HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        Map<String,Object>map=cartService.cartAll(phone);
        return ResponseServer.success(map);
    }

    @PostMapping
    @LoginAnnotation
    public ResponseServer cartChecked(String productId,HttpServletRequest request){
        String phone=(String) request.getAttribute("phone");
        cartService.cartChecked(productId,phone);
        return ResponseServer.success();
    }
    @PostMapping("addCartNum")
    @LoginAnnotation
    public ResponseServer addCartNum(HttpServletRequest request,String productId,String status,Integer count){
        String phone = (String) request.getAttribute("phone");
        cartService.addCartNum(phone,productId,status,count);
        return ResponseServer.success();
    }
    @DeleteMapping
    @LoginAnnotation
    public ResponseServer deleteProduct(HttpServletRequest request,String productId){
        String phone = (String) request.getAttribute("phone");
        Long sum=cartService.deleteProduct(phone,productId);
        if (sum==0){
            return ResponseServer.error();
        }
        else {
            return ResponseServer.success();
        }

    }
    @GetMapping("checkedAll")
    @LoginAnnotation
    public ResponseServer checkedAll(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        //System.out.println("进来了。");
        cartService.checkedAll(phone);
        return ResponseServer.success();
    }

}
