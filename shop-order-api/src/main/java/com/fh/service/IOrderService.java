package com.fh.service;

import com.fh.utils.response.ResponseServer;

public interface IOrderService {

    ResponseServer createOrder(Integer addressId, String phone);
}
