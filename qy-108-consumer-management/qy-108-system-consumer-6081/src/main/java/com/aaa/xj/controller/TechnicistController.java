package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Technicist;
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
    public ResultData qureyTechnicist(@RequestParam("userId") Long UserId){
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
     * @Description:技术人员根据userid分页查询
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/30 23:17
     */
    @PostMapping("/queryTechnicistByPage")
    public ResultData selectTechnicistByPage(@RequestParam("userId") Long userId,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = qyService.selectTechnicistByPage(userId, pageNo, pageSize);
        //判断查询结果是否为空
        if (null != pageInfo && !"".equals(pageInfo)){
            //不为空就返回成功信息
            return getSuccess(pageInfo);
        }
        return getFalse();
    }

    /**
     * @Description:根据主键id进行删除技术人员信息
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/31 10:18
     */
    @PostMapping("/deleteTechnicistKey")
    public ResultData deleteTechnicistKey(@RequestParam("id") Long id){
        Integer integer = qyService.deleteTechnicistKey(id);
        //判断删除首映的行数
        if (integer > 0){
            //大于0 说明删除成功返回自定义信息
            return deleteSuccess();
        }
        return deleteFalse();
    }

    /**
     * @Description: 根据主键id查看技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/5/31 21:58
     */
    @PostMapping("/queryOneTechnicist")
    public ResultData selectOneTechnicist(@RequestParam("id") Long id){
        List<Technicist> technicists = qyService.selectOneTechnicist(id);
        //判断查询结果是否为空
        if (null != technicists && !"".equals(technicists)){
            //不为空说明成功返回自定义成功信息
            return getSuccess(technicists);
        }
        return getFalse();
    }

    /**
     * @Description: 添加技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 20:24
     */
    @PostMapping("/insertTechnicist")
    public ResultData insertTechnicist(@RequestBody Technicist technicist){
        Integer integer = qyService.insertTechnicist(technicist);
        //判断添加受影响的行数
        if (integer>0){
            //大于0就返回成功自定义信息
            return super.addSuccess();
        }
        return super.addFalse();
    }

    /**
     * @Description: 查询要修改的技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/6/1 21:58
     */
    @PostMapping("/queryUpdateTechnicist")
    public ResultData selectUpdateTechnicist(@RequestParam("id") Long id){
        List<Technicist> technicists = qyService.selectUpdateTechnicist(id);
        //判断查询结果
        if (null != technicists && !"".equals(technicists)){
            //不为空就返回自定义成功信息值
            return super.getSuccess(technicists);
        }
        return super.getFalse();
    }


    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 16:43
     */
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        Integer integer = qyService.updateTechnicist(technicist);
        //判断修改受影响的行数
        if(integer > 0){
            //大于0返回自定义信息
            return updateSuccess();
        }
        return updateFalse();
    }

}
