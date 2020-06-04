package com.aaa.xj.service;

import com.aaa.xj.base.BaseModel;
import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.Mapping_unitMapper;
import com.aaa.xj.model.Mapping_unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 19:15
 */
@Service
public class Mapping_unitService extends BaseService<Mapping_unit> {

    @Autowired
    private Mapping_unitMapper mappingUnitMapper;

    /** 
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit> 
     * @Author: ygy
     * @Date: 2020/5/22 20:15 
     */
    public List<Mapping_unit> qureyMapping_unit(Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = mappingUnitMapper.qureyMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询的结果
            return mapping_units;
        }
        //为空就返回一个null
        return null;
    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  模糊查询 查询测绘单位名称
     * @date 2020/5/26
     * @param [mappingUnit]
     * @return java.util.List<com.aaa.xj.model.MappingUnit>
     */
    public List<Mapping_unit> fuzzyUnitName(String unitName, String ownedDistrict, String qualificationLevel) {
        // 调用 mappingUnitMapper 中的 fuzzyUnitName 方法，得到查询结果
        List<Mapping_unit> mappingUnitList = mappingUnitMapper.fuzzyUnitName(unitName, ownedDistrict, qualificationLevel);

        // 判断 结果是否为空
        if (mappingUnitList != null && mappingUnitList.size() > 0) {
            // 说明结果不为空，查询成功，返回结果
            return mappingUnitList;
        }else {
            // 查询失败，返回null
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
    public Mapping_unit selectUnitInfoById(Long id) {
        // 调用 mappingUnitMapper 中的 selectUnitInfoById 方法，得到查询结果
        Mapping_unit mappingUnit = mappingUnitMapper.selectUnitInfoById(id);

        // 判断 结果是否为空
        if (null != mappingUnit && !"".equals(mappingUnit)) {
            // 说明结果不为空，查询成功，返回结果
            return mappingUnit;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @Description: 查询要修改的测绘单位信息
     * @Param: [mappingUnit]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/5/29 22:03
     */
    public List<Mapping_unit> selectUpdateMappingUnit(Long id){
        //判断传进来的uid是否为空
        if (null != id && !"".equals(id)){
            //不为空进行查询操作
            List<Mapping_unit> mappingUnits = mappingUnitMapper.selectUpdateMappingUnit(id);
            //判断查询的信息是否为空
            if (null != mappingUnits && !"".equals(mappingUnits)){
                //不为空就返回查询的结果
                return mappingUnits;
            }
        }
        return null;
    }

    /**
     * @Description: 修改测绘单位信息
     * @Param: [mappingUnit]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/29 23:04
     */
    public Integer updaMappingUnit(Mapping_unit mappingUnit){

        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //把当前系统时间作为修改时间穿进去
        Mapping_unit mappingUnits = (Mapping_unit) mappingUnit.setModifyTime(format);
        //根据传进来的值进行修改
        Integer integer = mappingUnitMapper.updateMappingUnit(mappingUnits);
        //判断修改受影响的行数
        if (integer > 0){
            //大于0返回受影响的行数
            return integer;
        }
        return 0;
    }


    /**
     * @Author:  xj
     * @description
     *      单位资质统计
     * @Data: 2020/6/4
     * @param []
     * @Return:java.util.List<java.util.Map>
     */
    public List<Map> selectMappingUnitByLevel(){

        try {
            //调用查询方法
            List<Map> maps = mappingUnitMapper.selectMappingUnitByLevel();
            //判断是否查询成功
            if (null !=maps){
                return maps;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
