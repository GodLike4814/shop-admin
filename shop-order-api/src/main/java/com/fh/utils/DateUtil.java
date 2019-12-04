package com.fh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

public static final String yyyyMMhhmmss="yyyyMMddHHmmss";

public static  String getYyyyMMhhmmss(Date date,String pattern){
        if(date != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String result = simpleDateFormat.format(date);
            return result;
        }
        return "";
}
}
