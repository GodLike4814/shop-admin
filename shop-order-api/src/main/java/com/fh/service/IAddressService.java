package com.fh.service;

import com.fh.bean.AddressBean;
import com.fh.utils.response.ResponseServer;

public interface IAddressService {
    ResponseServer queryAddress(String phone);

    ResponseServer byOneAddress(Integer addressId);

    ResponseServer addAddress(AddressBean address);

    ResponseServer queryAddressById(Integer addressId);

    ResponseServer updateAddress(AddressBean address);
}
