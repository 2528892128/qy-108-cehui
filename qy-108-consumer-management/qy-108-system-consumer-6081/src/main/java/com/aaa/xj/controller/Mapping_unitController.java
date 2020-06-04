package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Mapping_unit;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 *  系统主页-测绘单位
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 19:16
 */
@RestController
public class Mapping_unitController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 20:28
     */
    @PostMapping("/qureyMapping_unit")
    public ResultData qureyMapping_unit(@RequestParam("userId") Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = iqyService.qureyMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询数据
            return getSuccess(mapping_units);
        }
        //为空就返回查询失败信息
        return getFalse();
    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  查询测绘单位名称
     *      模糊查询-动态sql
     * @date 2020/5/28
     * @param [unitName, ownedDistrict, qualificationLevel]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Mapping_unit>
     */
    @GetMapping("/fuzzyUnitName")
    public ResultData<Mapping_unit> getUnitName(String unitName, String ownedDistrict, String qualificationLevel) {
        // 调用 iqyService 中的 fuzzyUnitName 方法，得到查询结果
        List<Mapping_unit> mappingUnits = iqyService.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnits != null) {
            // 说明结果不为空，查询成功，使用系统消息 自定义返回值
            return getSuccess(mappingUnits);
        }else {
            // 查询失败，返回系统信息
            return getFalse();
        }

    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  查询测绘单位基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Mapping_unit>
     */
    @GetMapping("/selectUnitInfoById")
    public ResultData<Mapping_unit> selectUnitInfoById(Long id) {
        // 调用 iqyService 中的 selectUnitInfoById 方法，得到查询结果
        Mapping_unit mappingUnit = iqyService.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit) {
            // 说明结果不为空，查询成功，返回系统消息，自定义返回值
            return getSuccess(mappingUnit);
        }else {
            // 查询失败，返回系统信息
            return getFalse();
        }
    }

    /**
     * @Description:查询要修改的测绘单位信息
     * @Param: [id]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/6/2 10:49
     */
    @PostMapping("/queryUpdateMappingUnit")
    public ResultData selectUpdateMappingUnit(@RequestParam("id") Long id){
        List<Mapping_unit> mappingUnits = iqyService.selectUpdateMappingUnit(id);
        //判断查询结果
        if (null != mappingUnits && !"".equals(mappingUnits)){
            //不为空返回查询结果
            return getSuccess(mappingUnits);
        }
        return getFalse();
    }

    /**
     * @Description: 修改测绘单位信息
     * @Param: [mappingUnit]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/6/2 10:58
     */
    @PostMapping("/updateMappingUnit")
    public ResultData updateMappingUnit(@RequestBody Mapping_unit mappingUnit){
        Integer integer = iqyService.updateMappingUnit(mappingUnit);
        //判断修改受影响的行数
        if(integer > 0){
            //大于0返回成功信息
            return updateSuccess();
        }
        return updateFalse();
    }



}
