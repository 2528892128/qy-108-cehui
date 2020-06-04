package com.aaa.xj.controller;

import com.aaa.xj.model.ManProject;
import com.aaa.xj.service.ManProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ManProjectController {

    @Autowired
    private ManProjectService manProjectService;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询项目
     * @Data: 2020/5/21
     * @param manProject
     * @Return:java.util.List<com.aaa.xj.model.ManProject>
     */
    @PostMapping("/allPro")
    public List<ManProject> selectAllPros(@RequestBody ManProject manProject){

        try {
            //PageInfo<ManProject> manProjectPageInfo = manProjectService.queryListByPage(manProject,5,2);
            List<ManProject> manProjects = manProjectService.selectAllPros(manProject);
            return manProjects;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      通过id查询项目
     * @Data: 2020/5/21
     * @param id
     * @Return:com.aaa.xj.model.ManProject
     */
    @GetMapping("selectById")
    public ManProject selectById(@RequestParam("id") Long id){
        try {
            ManProject manProject = manProjectService.selectById(id);
            if (!"".equals(manProject) && null != manProject){
                return manProject;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *       通过id修改项目
     * @Data: 2020/5/21
     * @param manProject
     * @Return:java.lang.Integer
     */
    @PostMapping("/updateById")
    public Integer updateById(@RequestBody ManProject manProject){
        try {
            Integer integer = manProjectService.updateById(manProject);
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description: 新增测绘项目信息
     * @Param: [manProject]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/3 23:34
     */
    @PostMapping("/addManProject")
    public Integer addManProject(@RequestBody ManProject manProject){
        Integer integer = manProjectService.addManProject(manProject);
        if (integer > 0){
            return integer;
        }
        return 0;
    }


}
