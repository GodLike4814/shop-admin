package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.bean.CartBean;
import com.fh.service.ICartService;
import com.fh.utils.httpclient.HttpConnection;
import com.fh.utils.response.ResponseServer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements ICartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseServer addCart(Integer productId, String userPhone) {
        //获取购物车id
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        //根据商品id查询商品信息
        String url = "http://localhost:8092/productSearch/" + productId;
        String result = HttpConnection.doGet(url);
        JSONObject jsonObject= JSON.parseObject(result);
        System.out.println(jsonObject.get("data"));
        JSONObject productData=JSON.parseObject(jsonObject.get("data").toString());

        //讲数据加入购物车实体bean中
        CartBean cartBean=new CartBean();
        cartBean.setProductId(productData.getInteger("id"));
        cartBean.setProductName(productData.getString("name"));
        cartBean.setMainImg(productData.getString("mainImg"));
        cartBean.setPrice(productData.getBigDecimal("price"));
        //判断商品是否存在购物车
        if(redisTemplate.opsForHash().hasKey(cartId,productId)){
            CartBean cart= (CartBean) redisTemplate.opsForHash().get(cartId,productId);
            cartBean.setCount(cart.getCount()+1);
        }else{
            cartBean.setCount(1);
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal count = new BigDecimal(cartBean.getCount());
        BigDecimal subtotal = bigDecimal.add(cartBean.getPrice()).multiply(count);
        cartBean.setSubtotal(subtotal);
        cartBean.setIsChecked(true);


        //加入到redis
        redisTemplate.opsForHash().put(cartId,productId,cartBean);
        Long cartCount=redisTemplate.opsForHash().size(cartId);
        return ResponseServer.success(cartCount);
    }

    @Override
    public Map<String, Object> cartAll(String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);

        List<CartBean> cartList=redisTemplate.opsForHash().values(cartId);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (CartBean cart : cartList) {
            if(cart.getIsChecked()){
                bigDecimal = bigDecimal.add(cart.getSubtotal());
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cartList", cartList);
        map.put("total", bigDecimal);
        return map;
    }

    @Override
    public void cartChecked(String productId, String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        Integer a = Integer.parseInt(productId);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(cartId, a);
        cartBean.setIsChecked(!cartBean.getIsChecked());
        redisTemplate.opsForHash().put(cartId,a, cartBean);


    }

    @Override
    public void addCartNum(String phone, String productId,String status,Integer count) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        Integer a=Integer.parseInt(productId);
        CartBean cartBean=(CartBean) redisTemplate.opsForHash().get(cartId,a);
        if ("add".equals(status)){
            cartBean.setCount(cartBean.getCount()+1);
        }else if ("remove".equals(status)&&cartBean.getCount()>1){
            cartBean.setCount(cartBean.getCount()-1);
        }else if ("custom".equals(status)){
            cartBean.setCount(count);
        }
        cartBean.setSubtotal(cartBean.getPrice().multiply(new BigDecimal(cartBean.getCount())));
        redisTemplate.opsForHash().put(cartId,a,cartBean);
    }

    @Override
    public Long deleteProduct(String phone, String productId) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        Integer a=Integer.parseInt(productId);
        redisTemplate.opsForHash().delete(cartId, a);
        Long size = redisTemplate.opsForHash().size(cartId);
        return size;
    }

    @Override
    public void checkedAll(String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        List<CartBean> cartList=redisTemplate.opsForHash().values(cartId);
        boolean checked=true;
        for (CartBean cart:cartList){
            if (!cart.getIsChecked()){
                cart.setIsChecked(true);
                checked=false;

            }
            redisTemplate.opsForHash().put(cartId,cart.getProductId(), cart);
        }
        if (checked){
            for (CartBean cart:cartList){
                cart.setIsChecked(false);
                redisTemplate.opsForHash().put(cartId,cart.getProductId(), cart);
            }
        }
    }

    @Override
    public Long queryCartCount(String userPhone) {
        Long count;
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        count=redisTemplate.opsForHash().size(cartId);
        return count;

    }

    @Override
    public ResponseServer queryCartsChecked(String phone) {
      String  cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        List<CartBean> cartList = redisTemplate.opsForHash().values(cartId);
        ArrayList<CartBean> checkList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        long countSum = 0;
        BigDecimal countPrice = BigDecimal.valueOf(0.00);
        for (CartBean cart:cartList) {
            if(cart.getIsChecked()){
                BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
                checkList.add(cart);
                countSum+=cart.getCount();
                BigDecimal count = new BigDecimal(cart.getCount());
                countPrice = countPrice.add(bigDecimal.add(cart.getPrice()).multiply(count));
            }
        }
        map.put("checkList",checkList);
        map.put("countPrice",countPrice);
        map.put("countSum",countSum);
        return ResponseServer.success(map);
    }
}
