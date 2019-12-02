package com.fh.dao;

import com.fh.bean.TypeBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ITypeDao {
    List<TypeBean> queryTypeList();
}
