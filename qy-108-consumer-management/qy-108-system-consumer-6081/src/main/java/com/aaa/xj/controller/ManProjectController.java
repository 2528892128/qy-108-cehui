package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.User;
import com.aaa.xj.service.IQYService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "项目管理", tags = "项目管理接口")
public class ManProjectController extends BaseController {

    @Autowired
    private IQYService iqyService;
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询项目信息
     * @Data: 2020/5/21
     * @param user
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/allPro")
    public ResultData selectAllPro(ManProject manProject) {
        List<ManProject> manProjects = iqyService.selectAllPros(manProject);
        if (manProjects.size()>0){
            return super.getSuccess(manProjects);
        }
        return super.getFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据主键查询信息
     * @Data: 2020/5/21
     * @param id
     * @Return:com.aaa.xj.base.ResultData
     */
    @GetMapping("/selectById")
    public ResultData selectProById(Long id){
        ManProject manProject = iqyService.selectById(id);
        if (!"".equals(manProject) && null != manProject){
            return super.getSuccess(manProject);
        }
            return super.getFalse();
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id修改项目信息
     * @Data: 2020/5/21
     * @param manProject
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/updateById")
    public ResultData updateById(ManProject manProject){
        Integer integer = iqyService.updateById(manProject);
        if (integer>0){
            return super.updateSuccess();
        }
        return super.updateFalse();
    }

//    /**
//     * @Summary:
//     * @Author:  xj
//     * @description
//     *      根据项目类型查询
//     * @Data: 2020/5/22 10:48
//     * @param type
//     * @Return:com.aaa.xj.base.ResultData
//     */
//    @PostMapping("/selectByType")
//    public ResultData selectByType(String projectType,User user){
//        ResultData resultData = iqyService.selectByType(projectType, user);
//        return resultData;
//    }


}
