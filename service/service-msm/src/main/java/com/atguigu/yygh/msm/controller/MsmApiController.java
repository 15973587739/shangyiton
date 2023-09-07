package com.atguigu.yygh.msm.controller;

import com.alibaba.excel.util.StringUtils;
import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.msm.service.MsmService;
import com.atguigu.yygh.msm.util.ConstantPropertiesUtils;
import com.atguigu.yygh.msm.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    //生成微信扫描二维码
    //返回生成二维码需要参数
//    @GetMapping("getLoginParam")
//    @ResponseBody
//    public Result genQrConnect(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("appid", ConstantPropertiesUtils.ACCESS_KEY_ID);
//        map.put("scope","snsapi_login");
//        ConstantPropertiesUtils.r
//        map.put("redirect_uri","");
//
//    }

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，

        // 生成验证码，
        code = RandomUtil.getSixBitRandom();
        //调用service方法，通过整合短信服务进行发送
        boolean isSend = msmService.send(phone,code);
        //生成验证码放到redis里面，设置有效时间
        if(isSend) {
            //设置两分钟失效
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }
}
