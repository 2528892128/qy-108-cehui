package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.SpecialPostMapper;
import com.aaa.xj.model.SpecialPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SpecialPost> selectSpecialPost(Long userId){

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

}
