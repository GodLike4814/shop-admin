package com.fh.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fh.bean.AddressBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAddressDao {

    List<AddressBean> queryAddress(String phone);

    AddressBean byOneAddress(Integer addressId);

    void addAddress(AddressBean address);

    AddressBean queryAddressById(Integer addressId);

    void updateAddress(AddressBean address);
}
