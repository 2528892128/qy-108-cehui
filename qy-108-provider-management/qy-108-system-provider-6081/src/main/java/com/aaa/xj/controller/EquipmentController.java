package com.aaa.xj.controller;

import com.aaa.xj.model.Equipment;
import com.aaa.xj.service.EquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Equipment> selectEquipment(@RequestParam("userId") Long userId){
        try{
            //根据userID查询仪器设备
            List<Equipment> equipment = equipmentService.selectEquipment(userId);
            return equipment;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * liukai
     * 根据userid分页查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectEquipmentByPage")
    public PageInfo<Equipment> selectEquipmentByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize){
        PageInfo pageInfo = equipmentService.selectEquipmentByPage(userId, pageNo, pageSize);
        if (null !=pageInfo &&!"".equals(pageInfo) ){
            return pageInfo;
        }
        return null;
    }


    /**
     * liukai
     * 根据ID查询单条设备信息数据
     * @param id
     * @return
     */
    @PostMapping("/OneEquipment")
    public List<Equipment> selectOneEquipment(@RequestParam("id") Long id){
        try{
            //根据ID查询仪器设备
            List<Equipment> equipment = equipmentService.selectOneEquipment(id);
            return equipment;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * liukai
     * 根据实体进行新增仪器设备信息
     * @param equipment
     * @return
     */
    @PostMapping("/insertEquipment")
    public Integer insertEquipment(@RequestBody Equipment equipment){
        try{
            //根据前台传入实体数据增加仪器设备信息
            Integer integer = equipmentService.insertEquipment(equipment);
            return integer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 修改仪器设备前根据id进行查询数据
     * @param id
     * @return
     */
    @PostMapping("/selectByKey")
    public List<Equipment> selectByKey(@RequestParam("id") Long id){
        try{
            //根据id查询仪器设备
            List<Equipment> equipment = equipmentService.selectByKey(id);
            return equipment;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据实体数据修改仪器设备信息
     * @param equipment
     * @return
     */
    @PostMapping("/updateEquipment")
    public Integer updateEquipment(@RequestBody Equipment equipment){
        try{
            //根据i实体数据修改仪器设备信息
            Integer integer = equipmentService.updateEquipment(equipment);
            return integer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
     * liukai
     * 根据id进行删除仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/deleteByKey")
    public Integer deleteByKey(@RequestParam("id") Long id){
        try{
            //根据id进行删除仪器设备信息
            Integer integer = equipmentService.deleteByKey(id);
            return integer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
