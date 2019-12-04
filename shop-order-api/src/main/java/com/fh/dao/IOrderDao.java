package com.fh.dao;

import com.fh.bean.Order;
import com.fh.bean.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IOrderDao {

    Integer updateProductId(@Param("productId") int productId, @Param("count") Integer count);

    void insert(OrderItem orderItem);

    void insertOrder(Order order);
}
