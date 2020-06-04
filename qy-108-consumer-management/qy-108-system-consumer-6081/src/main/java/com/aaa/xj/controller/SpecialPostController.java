package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Equipment;
import com.aaa.xj.model.SpecialPost;
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
 * @create: 2020-05-25-2020/5/25 22:10
 */
@RestController
public class SpecialPostController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Description: 根据userId获取特殊人员信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/25 22:11
     */
    @PostMapping("/qureySpecialPost")
    public ResultData selectSpecialPost(@RequestParam("userId") Long userId){
        try {
            //根据userID查询信息
            List<SpecialPost> specialPosts = iqyService.selectSpecialPost(userId);
            return getSuccess(specialPosts);
        }catch (Exception e){
            e.printStackTrace();
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
    @PostMapping("/selectSpecialPostByPage")
    public ResultData<SpecialPost> selectSpecialPostByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize){
        PageInfo<SpecialPost> specialPostPageInfo = iqyService.selectSpecialPostByPage(userId, pageNo, pageSize);
        if (null != specialPostPageInfo && !"".equals(specialPostPageInfo)){
            return getSuccess(specialPostPageInfo);
        }
        return getFalse();
    }

    /**
     * liukai
     * 根据id查询单条特殊岗位信息
     * @param id
     * @return
     */
    @PostMapping("/selectOneSpecialPost")
    public ResultData selectOneSpecialPost(@RequestParam("id") Long id){
            //根据id查询信息
        List<SpecialPost> specialPosts = iqyService.selectOneSpecialPost(id);
        if (null != specialPosts && !"".equals(specialPosts)){
            return getSuccess(specialPosts);
        }
        return getFalse();
    }



    /**
     * liukia
     * 根据实体进行特殊岗位人员信息的新增
     * @param specialPost
     * @return
     */
    @PostMapping("/insertSpecialPost")
    public ResultData insertSpecialPost(@RequestBody SpecialPost specialPost){

            Integer integer = iqyService.insertSpecialPost(specialPost);
            if (integer>0){
                return addSuccess();
            }
        return addFalse();
    }


    /**
     * liukai
     * 进行修改特殊岗位人员前的查询
     * @param id
     * @return
     */
    @PostMapping("/selectByKeySpecialPost")
    public ResultData selectByKeySpecialPost(@RequestParam("id") Long id){
        //根据id查询数据
        List<SpecialPost> specialPosts = iqyService.selectByKeySpecialPost(id);
        //判断查询的数据不为空
        if (null != specialPosts && !"".equals(specialPosts)){
            //返回自定义的获取成功信息
            return getSuccess(specialPosts);
        }
        //为空返回自定义的获取失败信息
        return getFalse();
    }
    /**
     * liukai
     * 修改特殊岗位人员信息
     * @param specialPost
     * @return
     */
    @PostMapping("/updateSpecialPost")
        public ResultData updateSpecialPost(@RequestBody SpecialPost specialPost) {
            //根据实体查询数据
            Integer integer = iqyService.updateSpecialPost(specialPost);
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
     * 根据id删除特殊岗位人员信息
     * @param id
     * @return
     */
    @PostMapping("/deleteSpecialPost")
    public ResultData deleteSpecialPost(@RequestParam("id") Long id){
        //根据id调用iqyservic的deleteByKey
        Integer integer = iqyService.deleteSpecialPost(id);
        //判断数据不为空
        if (null != integer && !"".equals(integer)){
            //不为空返回自定义的删除成功信息
            return deleteSuccess();
        }
        //为空返回自定义的删除失败信息
        return deleteFalse();
    }


    }



