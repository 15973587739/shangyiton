package com.atguigu.yygh.user.api;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.user.utils.IpUtil;
import com.atguigu.yygh.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Api(tags = "用户")
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.login(loginVo);
        return Result.ok(info);
    }

}
