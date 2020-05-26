package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Technicist;
import com.aaa.xj.service.IQYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 10:31
 */
@RestController
public class TechnicistController extends BaseController {

    @Autowired
    private IQYService qyService;


    /**
     * @Description: 获取技术人员信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 10:51
     */
    @PostMapping("/qureyTechnicist")
    public ResultData qureyTechnicist(Long UserId){
        //根据userID获取技术人员信息
        List<Technicist> technicists = qyService.qureyTechnicist(UserId);
        //判断技术人员信息是否为空
        if (null != technicists){
            //不为空就返回带数据的信息
            return getSuccess(technicists);
        }
        return getFalse();
    }

    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 16:43
     */
    public ResultData updateTechnicist(Technicist technicist){
        //判断返回的Boolean类型
        if (qyService.updateTechnicist(technicist)){
            //如果为true就返回修改成功信息
            return updateSuccess();
        }
        //如果为true就返回修改失败信息
        return updateFalse();
    }

}
