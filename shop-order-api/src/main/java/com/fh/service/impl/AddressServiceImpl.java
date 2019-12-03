package com.fh.service.impl;

import com.fh.bean.AddressBean;
import com.fh.dao.IAddressDao;
import com.fh.service.IAddressService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDao addressDao;

    @Override
    public ResponseServer queryAddress(String phone) {

       List<AddressBean> addressList = addressDao.queryAddress(phone);
        return ResponseServer.success(addressList);
    }

    @Override
    public ResponseServer byOneAddress(Integer addressId) {
        return ResponseServer.success(addressDao.byOneAddress(addressId));
    }

    @Override
    public ResponseServer addAddress(AddressBean address) {
        address.setUpdateTime(new Date());
        addressDao.addAddress(address);
        return ResponseServer.success();
    }

    @Override
    public ResponseServer queryAddressById(Integer addressId) {
        AddressBean address = new AddressBean();
        AddressBean addressBean = addressDao.queryAddressById(addressId);
        return ResponseServer.success(addressBean);
    }

    @Override
    public ResponseServer updateAddress(AddressBean address) {
        address.setUpdateTime(new Date());
        addressDao.updateAddress(address);
        return ResponseServer.success();
    }
}
