package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.hosp.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospitalApiController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Integer page, @PathVariable Integer limit
//            HospitalQueryVo hospitalQueryVo
    ) {
        //显示上线的医院
        //hospitalQueryVo.setStatus(1);
//        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
//        return Result.ok(pageModel);
        return null;
//
    }
}
