package com.fh.utils;

import com.fh.github.wxpay.sdk.IWXPayDomain;
import com.fh.github.wxpay.sdk.WXPayConfig;
import com.fh.github.wxpay.sdk.WXPayConstants;

import java.io.InputStream;

public class MyWxConfig extends WXPayConfig {
    //注册账号的appkeyId
    @Override
    public String getAppID() {
        return "wxa1e44e130a9a8eee";
    }

    //商户ID
    @Override
    public String getMchID() {
        return "1507758211";
    }

    //支付密钥
    @Override
    public String getKey() {
        return "feihujiaoyu12345678yuxiaoyang123";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }
    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
