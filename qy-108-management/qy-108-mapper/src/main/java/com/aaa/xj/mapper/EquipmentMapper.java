package com.aaa.xj.mapper;

import com.aaa.xj.model.Equipment;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {

    /**
     * 查询全部仪器设备信息
     * @param userId
     * @return
     */
    List<Equipment> selectEquipment(Long userId);

    /**
     * liukai
     * 根据ID查询单条设备信息数据
     * @param id
     * @return
     */
    List<Equipment> selectOneEquipment(Long id);



    /**
     * liukai
     *根据实体id先获取仪器设备信息  在修改仪器设备信息
     * @param equipment
     * @return
     */
    Integer updateEquipment(Equipment equipment);
    List<Equipment> selectByKey(Long id);

    /**
     * liukai
     * 根据id进行删除仪器设备信息
     * @param id
     * @return
     */
    Integer deleteByKey(Long id);
}