package com.fh.dao;

import com.fh.bean.LoginBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ILoginDao {
    LoginBean queryPhone(String phone);

    LoginBean savePhone(LoginBean login);
}
