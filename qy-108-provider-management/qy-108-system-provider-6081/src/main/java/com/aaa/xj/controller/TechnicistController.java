package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.model.Technicist;
import com.aaa.xj.service.TechnicistService;
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
     * @Description: 技术人员根据userid分页查询
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/30 23:10
     */
    @PostMapping("/queryTechnicistByPage")
    public PageInfo selectTechnicistByPage(@RequestParam("userId") Long userId,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = technicistService.selectTechnicistByPage(userId, pageNo, pageSize);
        //判断结果是否为空
        if( null != pageInfo && !"".equals(pageInfo)){
            //不为空就不会返回结果
            return pageInfo;
        }
        return null;
    }

    /**
     * @Description: 通过主键id进行删除技术人员信息
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/31 10:15
     */
    @PostMapping("/deleteTechnicistKey")
    public Integer deleteTechnicistKey(@RequestParam("id") Long id){
        Integer integer = technicistService.deleteTechnicistKey(id);
        //判断删除受影响的行数
        if (integer > 0){
            //大于0 说明删除成功返回受影响的行数
            return integer;
        }
        return 0;
    }

    /**
     * @Description: 根据主键id查看技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/5/31 21:52
     */
    @PostMapping("/queryOneTechnicist")
    public List<Technicist> selectOneTechnicist(@RequestParam("id") Long id){
        List<Technicist> technicists = technicistService.selectOneTechnicist(id);
        //判断查询的结果是否为空
        if (null != technicists && !"".equals(technicists)){
            //不为空说明查询成功返回结果
            return technicists;
        }
        return null;
    }

    /**
     * @Description: 添加技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 20:20
     */
    @PostMapping("/insertTechnicist")
    public Integer insertTechnicist(@RequestBody Technicist technicist){
        Integer integer = technicistService.insertTechnicist(technicist);
        //判断受影响的行数
        if (integer>0){
            //大于0返回受影响的行数
            return integer;
        }
        return 0;
    }

    /**
     * @Description: 查询要修改的技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/6/1 20:40
     */
    @PostMapping("/queryUpdateTechnicist")
    public List<Technicist> selectUpdateTechnicist(@RequestParam("id") Long id){
        List<Technicist> technicists = technicistService.selectUpdateTechnicist(id);
        //判断查询的救国是否为空
        if (null != technicists && !"".equals(technicists)){
            //不为空返回查询结果
            return technicists;
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
    public Integer updateTechnicist(@RequestBody Technicist technicist){
        Integer integer = technicistService.updataTechnicist(technicist);
        //判断修改受影响行数
        if (integer > 0){
            // 大于0返回受影响行数
            return integer;
        }
        return 0;
    }


}
