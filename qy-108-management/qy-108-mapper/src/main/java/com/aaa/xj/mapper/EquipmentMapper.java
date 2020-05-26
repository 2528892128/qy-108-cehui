package com.aaa.xj.mapper;

import com.aaa.xj.model.Equipment;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {

    List<Equipment> selectEquipment(Long userId);

}