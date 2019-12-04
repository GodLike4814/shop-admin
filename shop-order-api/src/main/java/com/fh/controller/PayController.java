package com.fh.controller;

import com.fh.bean.PayLog;
import com.fh.github.wxpay.sdk.WXPay;
import com.fh.login.aop.LoginAnnotation;
import com.fh.rediskey.redisKeyUtil;
import com.fh.utils.DateUtil;
import com.fh.utils.MyWxConfig;
import com.fh.utils.response.ResponseServer;
import com.fh.utils.response.ServerEnum;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pays")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8081",exposedHeaders = "NOLONGIN")
public class PayController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/{outTradeNo}")
    @LoginAnnotation
    public ResponseServer createNative(@PathVariable String outTradeNo, HttpServletRequest request){
        String phone= (String) request.getAttribute("phone");
        //查询对用要支付的订单
        PayLog payLog= (PayLog) redisTemplate.opsForHash().get(redisKeyUtil.getWaitPayKey(phone),outTradeNo);
        if(payLog == null){
            return ResponseServer.error(ServerEnum.NO_ORDER_TO_PAY);
        }
        //调用支付接口
        MyWxConfig myWxConfig=new MyWxConfig();
        try{
            WXPay wxPay=new WXPay(myWxConfig);
            //存放参数的map集合
            Map<String,String>  data=new HashMap<String,String>();
            data.put("body","飞狐1904B电商支付--平台");
            //设置订单号
            data.put("out_trade_no",outTradeNo);
            //设置币种
            data.put("fee_type", "CNY");
            //设置支付的失效时间
            Date date=DateUtils.addMinutes(new Date(),2);
            data.put("time_expire", DateUtil.getYyyyMMhhmmss(date, DateUtil.yyyyMMhhmmss));
            //设置支付的金额
            //BigDecimalUtil.mul(payLog.getPayMoney()+"", "100").intValue();
            int payMoney=1;
            data.put("total_fee", payMoney+"");
            //设置接口的调用路径
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            // 此处指定为扫码支付
            data.put("trade_type", "NATIVE");
            Map<String, String> resulMap = wxPay.unifiedOrder(data);
            //验证接口是否能够正常访问
            String returnCode = resulMap.get("return_code");
            String returnMsg = resulMap.get("return_msg");
            if(!"SUCCESS".equalsIgnoreCase(returnCode)){
                return ResponseServer.error(99999,returnMsg);
            }
            //验证业务是否成功
            String resultCode=resulMap.get("result_code");
            String  errorCodeDes=resulMap.get("err_code_des");
            if(!"SUCCESS".equalsIgnoreCase(resultCode)){
                return ResponseServer.error(99999,errorCodeDes);
            }
            String url=resulMap.get("code_url");
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("codeUrl",url);
            map.put("outTradeNo",outTradeNo);
            map.put("payMoney",payLog.getPayMoney());
            return ResponseServer.success(map);
        }catch (Exception e){
            return ResponseServer.error(ServerEnum.CRATER_PAY_ERROR);
        }

    }
}
