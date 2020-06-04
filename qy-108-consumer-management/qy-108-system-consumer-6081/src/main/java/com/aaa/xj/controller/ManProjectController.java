package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.User;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    public ResultData selectAllPro(ManProject manProject, Integer pageNo,Integer pageSize) {
        PageInfo pageInfo = iqyService.selectAllPros(manProject, pageNo, pageSize);
        if (null !=pageInfo){
            return super.getSuccess(pageInfo);
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

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据项目类型查询
     * @Data: 2020/5/22 10:48
     * @param type
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/selectAllProjectResultByType")
    public ResultData selectAllProjectResultByType(@RequestBody ManProject manProject, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = iqyService.selectAllProjectResultByType(manProject, pageNo, pageSize);
        if (null !=pageInfo){
            return super.getSuccess(pageInfo);
        }
        return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据id删除项目信息
     * @Data: 2020/6/3
     * @param [id]
     * @Return:com.aaa.xj.base.ResultData
     */
    @DeleteMapping("deleteMappingProjectById")
    public ResultData deleteMappingProjectById(Long id){
        Boolean aBoolean = iqyService.deleteMappingProjectById(id);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFalse();
    }

    /**
     * @Author:  xj
     * @description
     * 查询测绘不同类别及是否完成的数量，用于统计图
     * @Data: 2020/6/3
     * @param []
     * @Return:com.aaa.xj.base.ResultData
     */
    @GetMapping("selectProjectByType")
    public ResultData selectProjectByType(){
        //调用方法
        List<Map> maps = iqyService.selectProjectByType();
        //判断查询结果是否为空
        if (null !=maps){
            return super.getSuccess(maps);
        }
        return super.getFalse();
    }

    /**
     * @Description: 新增测绘项目信息
     * @Param: [manProject]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/6/4 11:54
     */
    @PostMapping("/addManprodect")
    public ResultData addManProject(@RequestBody ManProject manProject){
        Integer integer = iqyService.addManProject(manProject);
        //判断新增受影响的行数
        if (integer>0){
            //大于0返回成功信息
            return addSuccess();
        }
        return addFalse();

    }
    
    
}
