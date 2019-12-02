package com.fh.service;

import com.fh.bean.CartBean;
import com.fh.utils.response.ResponseServer;

import java.util.Map;

public interface ICartService {
    ResponseServer addCart(Integer productId, String userPhone);

    Map<String, Object> cartAll(String phone);


    void cartChecked(String productId, String phone);

    void addCartNum(String phone, String productId,String status,Integer count);

    Long deleteProduct(String phone, String productId);

    void checkedAll(String phone);

    Long queryCartCount(String userPhone);
}
