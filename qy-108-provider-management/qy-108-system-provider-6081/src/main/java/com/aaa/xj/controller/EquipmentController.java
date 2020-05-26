package com.aaa.xj.controller;

import com.aaa.xj.model.Equipment;
import com.aaa.xj.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-25-2020/5/25 20:49
 */
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * @Description: 根据userID获取仪器设别信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Equipment>
     * @Author: ygy
     * @Date: 2020/5/25 20:51
     */
    @PostMapping("/qureyEquipment")
    public List<Equipment> selectEquipment(Long userId){
        try{
            //根据userID查询仪器设备
            List<Equipment> equipment = equipmentService.selectEquipment(userId);
            return equipment;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
