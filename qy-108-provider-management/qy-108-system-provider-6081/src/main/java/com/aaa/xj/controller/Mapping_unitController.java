package com.aaa.xj.controller;

import com.aaa.xj.model.Mapping_unit;
import com.aaa.xj.service.Mapping_unitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 19:16
 */
@RestController
public class Mapping_unitController {

    @Autowired
    private Mapping_unitService mapping_unitService;

    /**
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/5/22 20:23
     */
    @PostMapping("/qureyMapping_unit")
    public List<Mapping_unit> qureyMapping_unit(Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = mapping_unitService.qureyMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询结果
            return mapping_units;
        }
        //为空就返回一个null
        return null;
    }

}
