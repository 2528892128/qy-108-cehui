package com.aaa.xj.controller;

import com.aaa.xj.model.Mapping_unit;
import com.aaa.xj.service.Mapping_unitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private Mapping_unitService mappingUnitService;

    /**
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/5/22 20:23
     */
    @PostMapping("/qureyMapping_unit")
    public List<Mapping_unit> qureyMapping_unit(@RequestParam("userId") Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = mappingUnitService.qureyMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询结果
            return mapping_units;
        }
        //为空就返回一个null
        return null;
    }

    /**
     * @author ligen
     * @description
     *  模糊查询 查询测绘单位名称
     * @date 2020/5/26
     * @param [mappingUnit]
     * @return java.util.List<com.aaa.xj.model.MappingUnit>
     */
    @GetMapping("/fuzzyUnitName")
    public List<Mapping_unit> fuzzyUnitName(@RequestParam("unitName") String unitName,
                                            @RequestParam("ownedDistrict") String ownedDistrict,
                                            @RequestParam("qualificationLevel") String qualificationLevel) {
        // 调用 mappingUnitService 中的 fuzzyUnitName 方法，得到查询结果
        List<Mapping_unit> mappingUnits = mappingUnitService.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnits != null) {
            // 说明结果不为空，返回结果数据
            return mappingUnits;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  查询测绘单位基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.Mapping_unit
     */
    @GetMapping("/selectUnitInfoById")
    public Mapping_unit selectUnitInfoById(@RequestParam("id") Long id) {
        // 调用 mappingUnitService 中的 selectUnitInfoById 方法，得到查询结果
        Mapping_unit mappingUnit = mappingUnitService.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit) {
            // 说明结果不为空，查询成功，返回结果
            return mappingUnit;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

}
