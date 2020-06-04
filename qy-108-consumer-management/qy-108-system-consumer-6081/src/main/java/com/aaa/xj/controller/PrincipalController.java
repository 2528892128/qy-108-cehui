package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Principal;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
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
 * @create: 2020-05-20-2020/5/20 16:14
 */
@RestController
@Api(value = "负责人信息")
public class PrincipalController extends BaseController {

    @Autowired
    private IQYService qyService;

    /**
     * @Description:获取负责人信息
     * @Param: [id]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:39
     */
    @PostMapping("/qureyPrincipal")
    public ResultData QureyList(@RequestParam("userId") Long userId) {

        List<Principal> principals = qyService.qureyOne(userId);
        if (null != principals) {
            return getSuccess(principals);
        }
        return getFalse();
    }


    /**
     * @Description: 根据userId分页查询负责人信息
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/31 9:54
     */
    @PostMapping("/queryPrincipalByPage")
    public ResultData selectPrincipalByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = qyService.selectPrincipalByPage(userId, pageNo, pageSize);
        //判断查询结果是否为空
        if (null != pageInfo && !"".equals(pageInfo)){
            //不为空说明成功就返回自定义成功信息
            return getSuccess(pageInfo);
        }
        return getFalse();
    }

    /**
     * @Description: 通过userId删除负责人信息
     * @Param: [userId]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/30 22:41
     */
    @PostMapping("/deletePrincipal")
    public ResultData deletePrincipal(@RequestParam("userId") Long userId){
        Integer integer = qyService.deletePrincipal(userId);
        //判断删除受影响的行数
        if (integer > 0 ){
            //大于0说明删除成功返回自定义信息
            return deleteSuccess();
        }
        //不大于0说明删除失败返回自定义信息
        return deleteFalse();
    }

    /**
     * @Description: 根据主键id查看负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/5/31 21:05
     */
    @PostMapping("/queryOnePrincipal")
    public ResultData selectOnePrincipal(@RequestParam("id") Long id){
        List<Principal> principals = qyService.selectOnePrincipal(id);
        //判断查询结果是否为空
        if (null != principals && !"".equals(principals)){
            //不为空说明成功返回自定义成功信息
            return getSuccess(principals);
        }
        return getFalse();
    }

    /**
     * @Description: 增加负责人信息
     * @Param: [principal, uploadController]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/6/1 16:43
     */
//    @PostMapping("/insertPrincipal")
//    public ResultData insertPrincipal(@RequestBody Principal principal,@RequestParam("multipartFile") MultipartFile multipartFile){
//        //根据前端信息新增负责人信息
//        Integer integer = qyService.insertPrincipal(principal,multipartFile);
//        //判断新增负责人信息受影响的行数
//        if (integer > 0){
//            //大于0说明成功返回自定义成功信息
//            return addSuccess();
//        }
//        return addFalse();
//    }


    /**
     * @Description: 添加负责人信息
     * @Param: [files, type, name, idType, idNumber, age, sex, workYear, duty, title, major, mappingYear, userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/6/3 20:01
     */
    @PostMapping("/addPrincipal")
    public ResultData addPrincipal(@RequestParam("files") MultipartFile[] files,@RequestParam("type") String type,@RequestParam("name") String name,@RequestParam("idType") String idType,
                                   @RequestParam("idNumber") String idNumber,@RequestParam("age") Integer age,@RequestParam("sex") Integer sex,
                                   @RequestParam("workYear") Integer workYear,@RequestParam("duty") String duty,@RequestParam("title") String title,
                                   @RequestParam("major") String major,@RequestParam("mappingYear") Integer mappingYear,@RequestParam("userId") Long userId){
        return qyService.addPrincipal(files,type,name,idType,idNumber,age,sex,workYear,duty,title,major,mappingYear,userId);
    }




    /**
     * @Description: 查询要修改的负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/6/1 17:17
     */
    @PostMapping("/queryupdatePrincipal")
    public ResultData selectUpdatePrincipal(@RequestParam("id") Long id ){
        List<Principal> principals = qyService.selectUpdatePrincipal(id);
        //判断查询结果是否为空
        if(null != principals && !"".equals(principals)){
            //不为空返回自定义有数据信息
            return getSuccess(principals);
        }
        return getFalse();
    }


    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:42
     */
    @PostMapping("/updatePrincipal")
    public ResultData updatePrincipal(@RequestBody Principal principal,@RequestParam("multipartFile") MultipartFile multipartFile){
        Integer integer = qyService.updatePrincipal(principal,multipartFile);
        //判断修改受影响的行数
        if (integer > 0){
            //如果为 大于0返回成功信息
            return updateSuccess();
        }
        //如果为false返回失败信息
        return updateFalse();
    }

}