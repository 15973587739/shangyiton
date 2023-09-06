package com.atguigu.yygh.hosp.controller;


import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.hosp.service.ScheduleService;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Map;

//表示全部数据都是返回json类型的数据
@RestController
//请求路径注解
@RequestMapping("/admin/hosp/department")
//跨域注解
//@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode){
        //根据医院的编号查询所有的科室信息
        List<DepartmentVo> list =departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

}
