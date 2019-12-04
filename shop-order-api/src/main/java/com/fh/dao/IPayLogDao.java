package com.fh.dao;

import com.fh.bean.PayLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IPayLogDao {

    void insert(PayLog payLog);
}
