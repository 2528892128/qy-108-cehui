package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.Mapping_unitMapper;
import com.aaa.xj.model.Mapping_unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 19:15
 */
@Service
public class Mapping_unitService extends BaseService<Mapping_unit> {

    @Autowired
    private Mapping_unitMapper mapping_unitMapper;

    /** 
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit> 
     * @Author: ygy
     * @Date: 2020/5/22 20:15 
     */
    public List<Mapping_unit> qureyMapping_unit(Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = mapping_unitMapper.qureyMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询的结果
            return mapping_units;
        }
        //为空就返回一个null
        return null;
    }


}
