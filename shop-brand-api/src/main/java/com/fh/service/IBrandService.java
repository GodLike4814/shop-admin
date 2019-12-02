package com.fh.service;


import com.fh.utils.response.ResponseServer;

public interface IBrandService {
    ResponseServer queryBrandsByCateId(Integer pid);
}
