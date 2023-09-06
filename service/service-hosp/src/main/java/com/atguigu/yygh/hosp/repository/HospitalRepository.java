package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Hospital;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//操作医院信息的MongoDB库
@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {

    /**
     * 通过hosCode查询是否存在这个医院信息
     *
     * @param hoscode
     * @return
     */
    Hospital getHospitalByHoscode(String hoscode);



    /**
     * 根据hosname模糊查询
     * @param hosname
     * @return
     */
    List<Hospital> findHospitalByHosnameLike(String hosname);



}
