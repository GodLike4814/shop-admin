package com.fh.utils.httpclient;

import com.alibaba.fastjson.JSON;

import com.fh.utils.response.ResponseServer;
import com.fh.utils.response.ServerEnum;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpConnection {

    /*初始化连接的客户端*/
    private static CloseableHttpClient httpClient = null;

    static {
    /*static静态代码块 创建http连接 类加载时只执行一遍
    设置连接超时  目的是不回因为接口调不通造成大量的线程挂起，最总造成堵塞，tomcat直接崩溃。
    * 减少客户端创建的频率，节省服务器资源
    * setConnectionRequestTimeout:设置与服务器连接的超时时间
    * setSocketTimeout:设置访问接口的超时时间
    *
    * */
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * post修改
     *
     * @param url
     * @param parameterMap
     * @return
     */
    public static String doPost(String url, Map<String, String> parameterMap) {
        //声明http请求的方式
        HttpPost httpPost = new HttpPost(url);
        //处理参数
        if (parameterMap != null) {
            try {
                /*处理参数*/
                UrlEncodedFormEntity urlEncodedFormEntity = dispostParameter(parameterMap);
                //将参数加入到http请求中
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //执行请求
        return getResult(httpPost);
    }

    /**
     * PUT方法增加
     *
     * @return
     */
    public static String doPut(String url, Map<String, String> parameterMap){
        HttpPut httpPut = new HttpPut(url);
        if (parameterMap != null) {
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = dispostParameter(parameterMap);
                httpPut.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return getResult(httpPut);
    }

    /*处理请求参数*/
    public static UrlEncodedFormEntity dispostParameter(Map<String, String> parameterMap) throws UnsupportedEncodingException {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        parameterMap.entrySet().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            list.add(new BasicNameValuePair(key, value));
        });
        //处理参数
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
        return urlEncodedFormEntity ;
    }

    /**
     * get请求获取
     * @param url
     * @return
     */
    public static String doGet(String url){
        HttpGet httpGet=new HttpGet(url);
        //执行请求
       return getResult(httpGet);
    }

    /**
     * DELETE删除
     * @param url
     * @return
     */
    public static  String doDelete(String url){
        HttpDelete httpDelete = new HttpDelete(url);
        return getResult(httpDelete);
    }

    /**
     * 发送请求
     * @param target
     * @return
     */
    public static String  getResult(HttpRequestBase target){
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(target);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity,"UTF-8");
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            //服务器连接超时异常
            return JSON.toJSONString(ResponseServer.error(ServerEnum.ERROR));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.ERROR));
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.ERROR));
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
