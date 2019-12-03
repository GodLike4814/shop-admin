package com.fh.controller;

import com.fh.bean.AddressBean;
import com.fh.login.aop.LoginAnnotation;
import com.fh.service.IAddressService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders="NOLOGIN")
@RequestMapping("address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/queryAddress")
    @LoginAnnotation
    public ResponseServer queryAddress(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        return addressService.queryAddress(phone);
    }

    @PostMapping("/byOneAddress")
    @LoginAnnotation
    public ResponseServer byOneAddress(Integer addressId){
        return addressService.byOneAddress(addressId);
    }

    @PostMapping("/addAddress")
    @LoginAnnotation
    public ResponseServer addAddress(AddressBean address,HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        address.setPhone(phone);
        return addressService.addAddress(address);
    }

    /**
     * 回显
     * @param
     * @param
     * @return
     */
    @PostMapping("/queryAddressById")
    @LoginAnnotation
    public ResponseServer queryAddressById(Integer addressId){
        return addressService.queryAddressById(addressId);
    }

    @PostMapping("/updateAddress")
    @LoginAnnotation
    public ResponseServer updateAddress(AddressBean address){
        return addressService.updateAddress(address);
    }

}
