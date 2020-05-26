package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.EquipmentMapper;
import com.aaa.xj.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-25-2020/5/25 20:32
 */
@Service
public class EquipmentService extends BaseService<Equipment> {

    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * @Description: 根据userID获取仪器设备信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Equipment>
     * @Author: ygy
     * @Date: 2020/5/25 20:35
     */
    public List<Equipment> selectEquipment(Long userId){

        try {
            //判断userID是否为空
            if (!"".equals(userId)){
                //不为空就根据userid 查询
                List<Equipment> equipmentList = equipmentMapper.selectEquipment(userId);
                //判断查询结果是否为空
                if (null != equipmentList && !"".equals(equipmentList)){
                    //不为空返回查询结果
                    return equipmentList;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
