package com.aaa.xj.mapper;

import com.aaa.xj.model.Mapping_unit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface Mapping_unitMapper extends Mapper<Mapping_unit> {

    List<Mapping_unit> qureyMapping_unit(Long userId);

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  模糊查询 查询测绘单位名称
     * @date 2020/5/26
     * @param [mappingUnit]
     * @return java.util.List<com.aaa.xj.model.MappingUnit>
     */
    List<Mapping_unit> fuzzyUnitName(@Param("unitName") String unitName,
                                     @Param("ownedDistrict") String ownedDistrict,
                                     @Param("qualificationLevel") String qualificationLevel);

    /**
     * @author ligen
     * @description 系统主页-测绘单位
     *  查询测绘单位基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.Mapping_unit
     */
    Mapping_unit selectUnitInfoById(Long id);


    /**
     * @Description: 查询要修改的测绘单位信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/6/2 10:56
     */
    List<Mapping_unit> selectUpdateMappingUnit(Long id);

    /**
     * @Description: 修改测绘单位信息
     * @Param: [mappingUnit]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/2 10:56
     */
    Integer updateMappingUnit(Mapping_unit mappingUnit);

    /**
     * @Description: 查询测绘单位信息
     * @Param: []
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/6/3 0:05
     */
    List<Mapping_unit> selectMappingUnitPartList();

    List<Map> selectMappingUnitByLevel();

}