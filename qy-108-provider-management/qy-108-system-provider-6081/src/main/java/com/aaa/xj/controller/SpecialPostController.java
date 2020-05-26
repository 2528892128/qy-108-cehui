package com.aaa.xj.controller;

import com.aaa.xj.model.SpecialPost;
import com.aaa.xj.service.SpecialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
     * @Description: 根据userId获取特殊人员信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.SpecialPost>
     * @Author: ygy
     * @Date: 2020/5/25 22:04
     */
    @PostMapping("/qureySpecialPost")
    public List<SpecialPost> selectSpecialPost(Long userId){
        try {
            //根据userID查询信息
            List<SpecialPost> specialPosts = specialPostService.selectSpecialPost(userId);
            return specialPosts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
