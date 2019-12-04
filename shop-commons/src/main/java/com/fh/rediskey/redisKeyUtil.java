package com.fh.rediskey;

public class redisKeyUtil {

    public static String getCartIdKey(String phone){
     return "cartId"+phone;
    }

    public static String getWaitPayKey(String phone){
        return "pay_wait_"+phone;
    }
}
