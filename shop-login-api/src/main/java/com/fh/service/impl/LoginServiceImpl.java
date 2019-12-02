package com.fh.service.impl;

import com.fh.bean.LoginBean;
import com.fh.dao.ILoginDao;
import com.fh.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginDao loginDao;

    @Override
    public LoginBean queryPhone(String phone) {
        return loginDao.queryPhone(phone);
    }

    @Override
    public LoginBean savePhone(String phone) {
        LoginBean loginBean=new LoginBean();
        loginDao.savePhone(loginBean);
        return null;
    }


}
