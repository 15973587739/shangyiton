package com.atguigu.yygh.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    //根据数据id查询子数据列表
    @Override
    public List<Dict> findChildDate(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        //向list集合每个dict对象中设置hasChildren
        for(Dict dict : dictList){
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }

    @Override
    public void exportDictData(HttpServletResponse response) {

            //设置类型
            response.setContentType("application/vnd.ms-excel");
            //设置编码
            response.setCharacterEncoding("utf-8");
            String fileName = "dict";
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            //查询数据库
            List<Dict> dictList = baseMapper.selectList(null);
            //因为写入的时候引入的是vo类所以需要将Dict转换成DictEeVo
            List<DictEeVo> dictEeVoList =new ArrayList<>();
            for(Dict dict : dictList){
                DictEeVo dictEeVo = new DictEeVo();
                BeanUtils.copyProperties(dict,dictEeVo);
                dictEeVoList.add(dictEeVo);
            }
        try {
            //调用方法进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict")
                    .doWrite(dictEeVoList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //判断id下是否有子数据
    private  boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        //如果有数据就返回true
        return count>0;
    }

}
