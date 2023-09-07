package com.atguigu.yygh.user.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantWxPropertiesUtils implements InitializingBean {


    @Value("${wx.open.app_id}")
    private String appiid;

    @Value("${wx.open.redirect_url}")
    private String url;

    @Value("${wx.open.app_secret}")
    private String secret;

    @Value("${yygh.baseUrl}")
    private String yyghBaseUrl;



    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_REDIRECT_URL;
    public static String YYGH_BASE_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID=appiid;
        WX_OPEN_REDIRECT_URL=url;
        WX_OPEN_APP_SECRET=secret;
        YYGH_BASE_URL=yyghBaseUrl;
    }
}
