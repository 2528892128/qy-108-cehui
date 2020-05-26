package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.service.IQYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResultData selectEquipment(Long userId){
        List<Equipment> equipment = iqyService.selectEquipment(userId);
        if (null != equipment && !"".equals(equipment)){
            return getSuccess(equipment);
        }
        return getFalse();
    }

}
