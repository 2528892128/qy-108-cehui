package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.SpecialPost;
import com.aaa.xj.service.IQYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResultData selectSpecialPost(Long userId){
        try {
            //根据userID查询信息
            List<SpecialPost> specialPosts = iqyService.selectSpecialPost(userId);
            return getSuccess(specialPosts);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getFalse();
    }

}
