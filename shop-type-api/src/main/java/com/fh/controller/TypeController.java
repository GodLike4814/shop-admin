package com.fh.controller;

import com.fh.redis.IRedisService;
import com.fh.service.ITypeService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @Autowired
    private IRedisService redisService;

    /**
     * 查询所有的商品类别数据
     *
     * @return
     */
    @GetMapping
    public ResponseServer queryCategoryList() {
        //先查缓存再去查数据库
        //Boolean isExistKey = redisService.isExistKey("typeAll");
        Object typeList = null;
       // if (!isExistKey) {
            typeList = typeService.queryTypeList();
         //   redisService.setStringKeyAndValue("typeAll", typeList);
      //  } else {
        //    typeList = redisService.getStringValueByKey("typeAll");
     //   }
        return ResponseServer.success(typeList);
    }
}
