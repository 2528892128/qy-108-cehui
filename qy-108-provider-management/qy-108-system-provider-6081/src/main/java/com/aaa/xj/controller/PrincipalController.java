package com.aaa.xj.controller;

import com.aaa.xj.model.Principal;
import com.aaa.xj.service.PrincipalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-20-2020/5/20 15:42
 */
@RestController
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

    /**
     * @Description: 获取负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/20 16:11
     */
    @PostMapping("/qureyPrincipal")
    public List<Principal> qureyOne(@RequestParam("userId") Long userId){
        List<Principal> principals = principalService.qureyOne(userId);
        //判断查询结果是否为空
        if (null != principals){
            //不为空返回查询结果
            return principals;
        }
        return null;
    }

    /**
     * @Description: 根据userId 分页查询负责人信息
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/31 9:45
     */
    @PostMapping("/queryPrincipalByPage")
    public PageInfo selectPrincipalByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = principalService.selectPrincipalByPage(userId, pageNo, pageSize);
        //判断查询结果是否为空
        if (null != pageInfo && !"".equals(pageInfo)){
            //不为空就返回查询结果
            return pageInfo;
        }
        return null;
    }



    /**
     * @Description: 根据userId删除负责人信息
     * @Param: [userId]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/30 22:21
     */
    @PostMapping("/deletePrincipal")
    public Integer deletePrincipal(@RequestParam("userId") Long userId){
        Integer integer = principalService.deletePrincipal(userId);
        //判断删除的受影响的行数
        if (integer > 0){
            //大于0说明删除成功返回受影响的行数
            return integer;
        }
        return 0;
    }

    /**
     * @Description: 根据主键id查看负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/5/31 21:01
     */
    @PostMapping("/queryOnePrincipal")
    public List<Principal> selectOnePrincipal(@RequestParam("id") Long id){
        List<Principal> principals = principalService.selectOnePrincipal(id);
        //判断查询结果是否为空
        if (null != principals && !"".equals(principals)){
            //不为空说明成功返回结果
            return principals;
        }
        return null;
    }

    /**
     * @Description: 新增负责人信息
     * @Param: [principal, uploadService]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 16:34
     */
    @PostMapping("/inertPrincipal")
    public Integer insertPrincipal(@RequestBody Principal principal,@RequestParam("multipartFile") MultipartFile multipartFile){
        Integer integer = principalService.insertPrincipal(principal,multipartFile);
        //判断添加受影响的行数
        if (integer > 0){
            //大于0 说明成功返回受影响的行数
            return integer;
        }
        return 0;
    }

    /**
     * @Description: 查询要修改的负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/6/1 17:13
     */
    @PostMapping("/queryupdatePrincipal")
    public List<Principal> selectUpdatePrincipal(@RequestParam("id") Long id ){
        List<Principal> principals = principalService.selectUpdatePrincipal(id);
        //判断查询的结果是否为空
        if (null != principals && !"".equals(principals)){
            //不为空就返回查询结果
            return principals;
        }
        return principals;
    }



    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:26
     */
//    @PostMapping("/updatePrincipal")
//    public Integer updatePrincipal(@RequestBody Principal principal,MultipartFile multipartFile){
//        Integer integer = principalService.updateList(principal,multipartFile);
//        //判断修改所受影响的行数
//        if (integer > 0 ){
//            //大于0 说明成功返回受影响行数
//            return integer;
//        }
//        return 0;
//    }


}
