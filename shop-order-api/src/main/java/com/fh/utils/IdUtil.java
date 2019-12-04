package com.fh.utils;

import com.baomidou.mybatisplus.toolkit.IdWorker;

public class IdUtil {

    public static String getOrderId(){
        return IdWorker.getIdStr();
    }
}
