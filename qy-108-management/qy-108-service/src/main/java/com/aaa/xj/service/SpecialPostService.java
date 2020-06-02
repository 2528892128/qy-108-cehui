package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.SpecialPostMapper;
import com.aaa.xj.model.SpecialPost;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-25-2020/5/25 21:48
 */
@Service
public class SpecialPostService extends BaseService<SpecialPost> {


    @Autowired
    private SpecialPostMapper specialPostMapper;

    /**
     * @Description: 根据userId获取特殊人员信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.SpecialPost>
     * @Author: ygy
     * @Date: 2020/5/25 21:57
     */
    public List<SpecialPost> selectSpecialPost(@RequestParam("userId") Long userId){

        try{
            //判断userID是否为空
            if (!"".equals(userId)){
                //不为空根据userID查询
                List<SpecialPost> specialPosts = specialPostMapper.selectSpecialPost(userId);
                //判断查询结果是否为空
                if (null != specialPosts && !"".equals(specialPosts)){
                    //不为空返回查询结果
                    return specialPosts;
                }
                return null;
            }
        }catch (Exception e){
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
    public PageInfo selectSpecialPostByPage(Long userId, Integer pageNo , Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        try {
            //根据userid调用selectSpecialPost查询
            List<SpecialPost> specialPosts = specialPostMapper.selectSpecialPost(userId);
            //判断是否为空
            if (null != specialPosts && !"".equals(specialPosts)) {
                PageInfo<SpecialPost> specialPostPageInfo = new PageInfo<SpecialPost>(specialPosts);
                return specialPostPageInfo;
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * liukai
     * 根据id进行查询单条特殊岗位人员数据
     * @param id
     * @return
     */
    public List<SpecialPost> selectOneSpecialPost(@RequestParam("id") Long id){

        try{
            //判断ID是否为空
            if (!"".equals(id)){
                //不为空根据ID查询
                List<SpecialPost> specialPosts = specialPostMapper.selectOneSpecialPost(id);
                //判断查询结果是否为空
                if (null != specialPosts && !"".equals(specialPosts)){
                    //不为空返回查询结果
                    return specialPosts;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * iukai
     * 根据实体进行新增特殊岗位人员信息
     * @param specialPost
     * @return
     */
    public Integer insertSpecialPost(SpecialPost specialPost){

        try{
            //判断是否为空
            if (!"".equals(specialPost)) {
                //调用insert方法
                Integer integer = specialPostMapper.insert(specialPost);
                //判断受影响行数大于0
                if (integer>0){
                    //大于0则返回受影响行数
                    return integer;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * liukai
     * 修改特殊岗位信息前进行id查询
     * @param id
     * @return
     */
    public List<SpecialPost> selectByKeySpecialPost(Long id) {
        try {
            //判断ID是否为空
            if (!"".equals(id)){
                //不为空就根据id查询单个数据
                List<SpecialPost> specialPosts = specialPostMapper.selectByKeySpecialPost(id);
                //判断查询结果是否为空
                if (null != specialPosts && !"".equals(specialPosts)){
                    //不为空返回查询结果
                    return specialPosts;
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
     *根据实体修改特殊岗位人员信息
     * @param
     * @return
     */
    public Integer updateSpecialPost(SpecialPost specialPost){

        try{
            //判断是否为空
            if (!"".equals(specialPost)){
                //不为空就修改数据
                Integer integer = specialPostMapper.updateSpecialPost(specialPost);
                //判断结果是否为空
                if ((null != specialPost) && !"".equals(specialPost)){
                    //不为空返回结果
                    return integer;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (Exception e) {
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
    public Integer deleteSpecialPost(@RequestParam("id") Long id){
        try {
            //判断id是否为空
            if (!"".equals(id)){
                //不为空就 调用删除
                Integer integer = specialPostMapper.deleteSpecialPost(id);
                //判断结果是否为空
                if (null != id && !"".equals(id)){
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
