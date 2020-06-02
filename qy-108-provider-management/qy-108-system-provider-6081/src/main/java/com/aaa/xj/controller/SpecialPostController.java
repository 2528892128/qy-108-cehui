package com.aaa.xj.controller;

import com.aaa.xj.model.SpecialPost;
import com.aaa.xj.service.SpecialPostService;
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
 * @create: 2020-05-25-2020/5/25 22:03
 */
@RestController
public class SpecialPostController {

    @Autowired
    private SpecialPostService specialPostService;

    /**
     * @Description: 根据userId获取特殊岗位人员信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.SpecialPost>
     * @Author: ygy
     * @Date: 2020/5/25 22:04
     */
    @PostMapping("/qureySpecialPost")
    public List<SpecialPost> selectSpecialPost(@RequestParam("userId") Long userId){
        try {
            //根据userID查询信息
            List<SpecialPost> specialPosts = specialPostService.selectSpecialPost(userId);
            return specialPosts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
    public PageInfo<SpecialPost> selectSpecialPostByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize){
         //根据参数进行调用selectSpecialPostByPage
        PageInfo pageInfo = specialPostService.selectSpecialPostByPage(userId, pageNo, pageSize);
        //判断是否为空
        if (null !=pageInfo &&!"".equals(pageInfo) ){
            //不为空返回pageInfo
            return pageInfo;
        }
        return null;
    }

    /**
     * liukai
     * 根据id进行查询单条特殊岗位人员数据
     * @param id
     * @return
     */
    @PostMapping("/selectOneSpecialPost")
    public List<SpecialPost> selectOneSpecialPost(@RequestParam("id") Long id){
        try {
            //根据id查询信息
            List<SpecialPost> specialPosts = specialPostService.selectOneSpecialPost(id);
            return specialPosts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * liukai
     * 根据实体进行新增特殊岗位人员信息
     * @param specialPost
     * @return
     */
    @PostMapping("/insertSpecialPost")
    public Integer insertSpecialPost(@RequestBody SpecialPost specialPost){

        try{
            //调用insertSpecialPost方法
            Integer integer = specialPostService.insertSpecialPost(specialPost);
            //判断受影响行数大于0
            if (integer>0){
                //大于0返回integer
                return integer;
            }else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * liukai
     * 修改前根据id进行查询数据
     * @param id
     * @return
     */
    @PostMapping("/selectByKeySpecialPost")
    public List<SpecialPost> selectByKeySpecialPost(@RequestParam("id") Long id){
        try{
            //根据id查询仪器设备
            List<SpecialPost> specialPosts = specialPostService.selectByKeySpecialPost(id);
            return specialPosts;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * liukai
     * 根据实体数据修改特殊岗位人员信息
     * @param specialPost
     * @return
     */
    @PostMapping("/updateSpecialPost")
    public Integer updateSpecialPost(@RequestBody SpecialPost specialPost){
        try{
            //修改仪器设备信息
            Integer integer = specialPostService.updateSpecialPost(specialPost);
            return integer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * liukai
     * 根据id进行删除特殊岗位人员信息
     * @param id
     * @return
     */
    @PostMapping("/deleteSpecialPost")
    public Integer deleteSpecialPost(@RequestParam("id") Long id){
        try{
            //根据id进行删除
            Integer integer = specialPostService.deleteSpecialPost(id);
            return integer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
