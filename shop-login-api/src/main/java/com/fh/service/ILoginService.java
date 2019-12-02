package com.fh.service;

import com.fh.bean.LoginBean;

public interface ILoginService {
    LoginBean queryPhone(String phone);

    LoginBean savePhone(String phone);
}
