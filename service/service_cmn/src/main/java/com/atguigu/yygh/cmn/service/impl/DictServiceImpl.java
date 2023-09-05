package com.atguigu.yygh.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.atguigu.yygh.cmn.listener.DictListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "DictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {



    //根据数据id查询子数据列表
    @Cacheable(value = "dict",keyGenerator = "keyGenerator") //缓存注解
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

    //导出数据
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

    //导入数据字典
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importDictData(MultipartFile file) {
        try {
//            file.getInputStream()输入流用于读取表格数据，读取表格中的数据为DictEeVo类型，DictListener监听器用于处理
//            baseMapper自定义了有参构造函数需要操作数据库
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
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

    private Dict getByDictCode(String dictCode){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code",dictCode);
        Dict dict =  baseMapper.selectOne(wrapper);
        return dict;
    }

    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    @Override
    public String getNameByParentDictCodeAndValue(String parentDictCode, String value) {
        //如果value能唯一定位数据字典，parentDictCode可以传空，例如：省市区的value值能够唯一确定
        if(StringUtils.isEmpty(parentDictCode)) {
            Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("value", value));
            if(null != dict) {
                return dict.getName();
            }
        } else {
            Dict parentDict = this.getByDictCode(parentDictCode);
            if(null == parentDict) return "";
            Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id", parentDict.getId()).eq("value", value));
            if(null != dict) {
                return dict.getName();
            }
        }
        return "";
    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        Dict codeDict = this.getByDictCode(dictCode);
        if(null == codeDict) return null;
        return this.findChildDate(codeDict.getId());
    }

    @Override
    public String getDictName(String dictCode, String value) {
        //如果dictCode为空，直接根据value查询
        if(StringUtils.isEmpty(dictCode)){
            //直接根据value查询
            QueryWrapper<Dict> queryWrapper =new QueryWrapper();
            queryWrapper.eq("value",value);
            Dict dict = baseMapper.selectOne(queryWrapper);
            return dict.getName();
        }else{
            //根据dictCode和value查询
            Dict dict =this.getByDictCode(dictCode);
            Long id =dict.getId();
            //根据parent_id和value查询
            Dict finalDict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id",id).eq("value",value));
            return finalDict.getName();
        }
    }


    @Override
    public String getDictName(String value) {
        return this.getDictName("",value);
    }


}
