package com.aaa.xj.controller;

import com.aaa.xj.model.ManProject;
import com.aaa.xj.service.ManProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    public PageInfo selectAllPros(@RequestBody ManProject manProject , @RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){

        try {
            PageInfo<ManProject> manProjectPageInfo = manProjectService.selectAllPros(manProject, pageNo, pageSize);
            return manProjectPageInfo;
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
     * @Author:  xj
     * @description
     *      通过项目类型查询
     * @Data: 2020/5/21
     * @param [manProject, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("selectAllProjectResultByType")
    public PageInfo selectAllProjectResultByType(@RequestBody ManProject manProject,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        try {
            PageInfo pageInfo = manProjectService.selectMappingProjectByType(manProject, pageNo, pageSize);
            if (null !=pageInfo){
                return pageInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      根据id删除项目信息
     * @Data: 2020/6/3
     * @param [id]
     * @Return:java.lang.Boolean
     */
    @DeleteMapping("deleteMappingProjectById")
    public Boolean deleteMappingProjectById(@RequestParam("id") Long id){

        Boolean aBoolean = null;
        try {
            //调用删除的方法
            aBoolean = manProjectService.deleteMappingProjectById(id);
            if (aBoolean){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     * 查询测绘不同类别及是否完成的数量，用于统计图
     * @Data: 2020/6/3
     * @param []
     * @Return:java.lang.Boolean
     */
    @GetMapping("selectProjectByType")
    public List<Map> selectProjectByType(){
        //调用查询方法
        List<Map> maps = manProjectService.selectProjectType();
        if (null !=maps && ! maps.isEmpty()){
            return maps;
        }
        return null;
    }

}
