package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.model.Technicist;
import com.aaa.xj.service.TechnicistService;
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
 * @create: 2020-05-22-2020/5/22 10:25
 */
@RestController
public class TechnicistController{

    @Autowired
    private TechnicistService technicistService;

    /**
     * @Description: 获取技术人员信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 10:29
     */
    @PostMapping("/qureyTechnicist")
    public List<Technicist> qureyTechnicist(@RequestParam("userId") Long userId){
        List<Technicist> technicist = technicistService.qureyTechnicist(userId);
        if (null != technicist){
            return technicist;
        }
        return null;
    }

    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Boolean
     * @Author: ygy
     * @Date: 2020/5/22 16:36
     */
    @PostMapping("/updateTechnicist")
    public Boolean updateTechnicist(@RequestBody Technicist technicist){
        return technicistService.updataTechnicist(technicist);
    }


}
