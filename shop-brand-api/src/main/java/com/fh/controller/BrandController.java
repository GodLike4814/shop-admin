package com.fh.controller;


import com.fh.service.IBrandService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 根据商品类别ID查询品牌信息
     *
     * @param pid
     * @return
     */
    @GetMapping("/{pid}")
    public ResponseServer queryBrandsByCateId(@PathVariable("pid") Integer pid) {
        return brandService.queryBrandsByCateId(pid);
    }
}
