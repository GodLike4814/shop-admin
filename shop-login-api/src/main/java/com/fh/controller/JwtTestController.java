package com.fh.controller;

import com.fh.utils.jwt.JwtUtils;
import com.fh.utils.response.ResponseServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081",maxAge = 3600)
@RequestMapping("jwt")
public class JwtTestController {

    @PostMapping("jwtTest")
    public ResponseServer jwtTest(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",1);
        String token = JwtUtils.createToken(map);
        return ResponseServer.success(token);
    }
    @PostMapping("getJwtTest")
    public ResponseServer jwtTest(String token){
        ResponseServer responseServer = JwtUtils.resolverToken(token);
        System.out.println(responseServer.getCode());
        System.out.println(token);
        return ResponseServer.success(token);
    }

}
