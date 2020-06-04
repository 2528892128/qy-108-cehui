package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Equipment;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-25-2020/5/25 20:59
 */
@RestController
public class EquipmentController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Description: 根据userID获取仪器设备信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/25 21:03
     */
    @PostMapping("/qureyEquipment")
    public ResultData selectEquipment(@RequestParam("userId") Long userId){
        List<Equipment> equipment = iqyService.selectEquipment(userId);
        if (null != equipment && !"".equals(equipment)){
            return getSuccess(equipment);
        }
        return getFalse();
    }

    /**
     * liukai
     * 分页查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectEquipmentByPage")
    public ResultData<Equipment> selectEquipmentByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize){
        //调用selectEquipmentByPage方法
        PageInfo<Equipment> equipmentPageInfo = iqyService.selectEquipmentByPage(userId, pageNo, pageSize);
        //判断是否为空
        if (null != equipmentPageInfo && !"".equals(equipmentPageInfo)){
            return super.getSuccess(equipmentPageInfo);
        }
        return getFalse();
    }

    /**
     * liukai
     * 根据id查询单条仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/OneEquipment")
    public ResultData selectOneEquipment(@RequestParam("id") Long id){
        //根据id进行单条数据查询
        List<Equipment> equipment = iqyService.selectOneEquipment(id);
        //判断是否为空
        if (null != equipment && !"".equals(equipment)){
            //不为空返回getSuccess
            return getSuccess(equipment);
        }
        return getFalse();
    }


    /**
     * liukai
     * 根据实体新增仪器设备信息
     * @param equipment
     * @return
     */
    @PostMapping("/insertEquipment")
    public ResultData insertEquipment(@RequestBody Equipment equipment){
        //根据实体数据新增设备信息
        Integer integer = iqyService.insertEquipment(equipment);
        //判断是否为空
        if (null != equipment && !"".equals(equipment)){
            //不为空返回addsucss
            return addSuccess();
        }
        return addFalse();
    }


    /**
     * liukai
     * 根据id查询要修改的仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/selectByKey")
    public ResultData selectByKey(@RequestParam("id") Long id){
        //根据id调用iqyservic的selectByKey查询数据
        List<Equipment> equipment = iqyService.selectByKey(id);
        //判断查询的数据不为空
        if (null != equipment && !"".equals(equipment)){
            //返回自定义的获取成功信息
            return getSuccess(equipment);
        }
        //为空返回自定义的获取失败信息
        return getFalse();
    }

    /**
     * liukai
     * 根据id进行修改仪器设备信息
     * @param equipment
     * @return
     */
    @PostMapping("/updateEquipment")
    public ResultData updateEquipment(@RequestBody Equipment equipment){
        //根用iqyservic的updateEquipment修改数据
        Integer integer = iqyService.updateEquipment(equipment);
        //判断要修改的数据不为空
        if (integer>0){
            //不为空返回自定义的修改成功信息
            return updateSuccess();
        }
        //为空返回自定义的修改失败信息
        return updateFalse();
    }


    /**
     * liukai
     * 根据id删除仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/deleteByKey")
    public ResultData deleteByKey(@RequestParam("id") Long id){
        //调用iqyservic的deleteByKey方法
        Integer integer = iqyService.deleteByKey(id);
        //判断数据不为空
        if (integer>0){
            //不为空返回自定义的删除成功信息
            return deleteSuccess();
        }
        //为空返回自定义的删除失败信息
        return deleteFalse();
    }






}
