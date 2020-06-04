package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.EquipmentMapper;
import com.aaa.xj.model.Equipment;
import com.aaa.xj.model.Technicist;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * liukai
     * 根据userid分页查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo selectEquipmentByPage(Long userId, Integer pageNo , Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        try {
            List<Equipment> equipment = equipmentMapper.selectEquipment(userId);
            if (null != equipment && !"".equals(equipment)) {
                PageInfo<Equipment> pageInfo = new PageInfo< Equipment >(equipment);
                return pageInfo;
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * * liukai
     *根据ID查询单条设备信息数据
     * @param id
     * @return
     */
    public List<Equipment> selectOneEquipment(Long id) {

        try {
            //判断ID是否为空
            if (!"".equals(id)){
                //不为空就根据id查询
                List<Equipment> equipmentList = equipmentMapper.selectOneEquipment(id);
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


    /**
     * liukai
     * 根据实体进行新增仪器设备信息
     * @param equipment
     * @return
     */
    public Integer insertEquipment(Equipment equipment) {

        try {
            //判断实体是否为空
            if (!"".equals(equipment)){
                //不为空就新增
                Integer integer = equipmentMapper.insert(equipment);
                //判断结果是否为空
                if (integer>0){
                    //不为空返回查询结果
                    return integer;
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


    /**
     * liukai
     * 修改前的根据id查询
     * @param id
     * @return
     */
    public List<Equipment> selectByKey(Long id) {
        try {
            //判断ID是否为空
            if (!"".equals(id)){
                //不为空就根据id查询单个数据
                List<Equipment> equipmentList = equipmentMapper.selectByKey(id);
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
    /**
     * liukai
     * 修改仪器设备信息
     * @param equipment
     * @return
     */
    public Integer updateEquipment(Equipment equipment) {
        try {
            //判断实体是否为空
            if (!"".equals(equipment)){
                //不为空就调用修改方法
                Integer integer = equipmentMapper.updateEquipment(equipment);
                //判断结果是否为空
                if (integer>0){
                    //不为空返回查询结果
                    return integer;
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


    /**
     * liukai
     * 根据id删除仪器设备信息
     * @param id
     * @return
     */
    public Integer deleteByKey(Long id) {

        try {
            //判断id是否为空
            if (!"".equals(id)){
                //不为空就 调用删除
                Integer integer = equipmentMapper.deleteByKey(id);
                //判断结果是否为空
                if (integer>0){
                    //不为空返回查询结果
                    return integer;
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
