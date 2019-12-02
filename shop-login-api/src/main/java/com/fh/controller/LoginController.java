package com.fh.controller;

import com.fh.bean.LoginBean;
import com.fh.commons.message.SendCode;
import com.fh.service.ILoginService;
import com.fh.utils.jwt.JwtUtils;
import com.fh.utils.response.ResponseServer;
import com.fh.utils.response.ServerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "http://localhost:8081",maxAge = 3600)
public class LoginController {

    @Autowired
    ILoginService loginService;
    @Autowired
    RedisTemplate redisTemplate;
    @PostMapping("postCode")
    public ResponseServer postCode(String phone) throws Exception {
        Object o = SendCode.messageCode(phone);
        redisTemplate.opsForValue().set("code_"+phone,o);
        return ResponseServer.success();
    }

    @PostMapping("phoneLogin")
    public ResponseServer phoneLogin(String phone,String code) {
        //Object phoneCode = redisTemplate.opsForValue().get("code_"+phone);
        //if (phoneCode==null) return ResponseServer.error();
       // if (phoneCode.toString().equals(code)){
            LoginBean login=loginService.queryPhone(phone);
            if (login==null) {
                loginService.savePhone(phone);
            }
            redisTemplate.opsForValue().set("cartid_"+phone,login.getCartId());
           // redisTemplate.delete("code_"+phone);
            Map map=new HashMap();
            map.put("phone",phone);
            String token = JwtUtils.createToken(map);
            return ResponseServer.success(token);
       // }else {
          //  return ResponseServer.error(ServerEnum.ERROR);
       // }

    }
}
