package com.aaa.xj.controller;

import com.aaa.xj.model.Dept;
import com.aaa.xj.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  部门管理
 * @create 2020-05-30 09:45
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * @author ligen
     * @description 部门管理
     *  递归查询
     *      根据 parentId（父id）查询该部门及其子部门
     * @date 2020/5/30
     * @param [parentId]
     * @return java.util.List<com.aaa.xj.model.Dept>
     */
    @GetMapping("/selectAllDeptByParentId")
    public List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Integer parentId) {
        // 调用 deptService 中的 selectAllDeptByParentId 方法，得到查询结果
        List<Dept> allDept = deptService.selectAllDeptByParentId(parentId);

        // 判断 结果是否为空
        if (allDept != null) {
            // 说明结果不为空，返回查询的结果
            return allDept;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询-动态sql
     *      查询条件：部门名称 创建时间区间
     * @date 2020/6/1
     * @param [map]
     * @return java.util.List<com.aaa.xj.model.Dept>
     */
    @PostMapping("/selectDeptInfoByField")
    public List<Dept> selectDeptInfoByField(@RequestBody Map map) {
        // 调用 deptService 中的 selectDeptInfoByField 方法，得到查询结果
        List<Dept> deptList = deptService.selectDeptInfoByField(map);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明结果不为空，查询成功，返回查询的结果
            return deptList;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询部门信息，根据主键id查询部门的信息
     * @date 2020/5/30
     * @param [deptID]
     * @return com.aaa.xj.model.Dept
     */
    @GetMapping("/selectDeptByDeptId")
    public Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId) {
        // 调用 deptService 中的 selectDeptByDeptId 方法，得到查询结果
        Dept dept = deptService.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if (dept != null) {
            // 说明结果不为空，返回查询的结果
            return dept;
        }else {
            // 返回null
            return null;
        }
    }


    /**
     * @author ligen
     * @description
     *  新增部门信息
     * @date 2020/5/30
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/insertDept")
    public Boolean insertDept(@RequestBody Dept dept) {
        // 调用 deptService 中的 insertDept 方法，得到结果
        Boolean aBoolean = deptService.insertDept(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，新增成功，返回true
            return true;
        }else {
            // 新增失败，返回false
            return false;
        }
    }

    /**
     * @author ligen
     * @description
     *  通过主键 执行删除操作
     * @date 2020/5/30
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    public Boolean deleteDeptByPrimaryKey(@RequestBody Dept dept) {
        // 调用 deptService 中的 deleteDeptByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.deleteDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，删除成功 返回true
            return true;
        }else {
            // 删除失败，返回false
            return false;
        }
    }

    /**
     * @author ligen
     * @description
     *  批量删除 调用父类的批量删除方法（根据主键），执行删除操作
     * @date 2020/5/30
     * @param [ids]
     * @return java.lang.Boolean
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    public Boolean batchDeleteByPrimaryKey(@RequestBody List<Object> ids) {
        // 调用 deptService 中的 batchDeleteByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.batchDeleteByPrimaryKey(ids);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，删除成功 返回true
            return true;
        }else {
            // 删除失败，返回false
            return false;
        }
    }

    /**
     * @author ligen
     * @description
     *  修改，通过主键-修改部门信息
     * @date 2020/5/30
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateDeptByPrimaryKey")
    public Boolean updateDeptByPrimaryKey(@RequestBody Dept dept) {
        // 调用 deptService 中的 updateDeptByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.updateDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，修改成功 返回true
            return true;
        }else {
            // 修改失败，返回false
            return false;
        }
    }


}
