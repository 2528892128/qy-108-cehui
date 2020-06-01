package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Dept;
import com.aaa.xj.service.IQYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  部门管理
 * @create 2020-05-30 09:56
 */
@RestController
public class DeptController extends BaseController {
    @Autowired
    private IQYService iqyService;

    /**
     * @author ligen
     * @description 部门管理
     *  递归查询
     *      根据 parentId（父id）查询该部门及其子部门
     * @date 2020/5/30
     * @param [parentId]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @GetMapping("/getAllDeptByParentId")
    public ResultData<Dept> getAllDeptByParentId(Long parentId) {
        // 调用 iqyService 中的 selectAllDeptByParentId 方法，得到查询结果
        List<Dept> deptList = iqyService.selectAllDeptByParentId(parentId);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明查询成功，使用系统消息
            return getSuccess(deptList);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询-动态sql
     *      查询条件：部门名称 创建时间区间
     * @date 2020/6/1
     * @param [map]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @PostMapping("/getDeptInfoByField")
    public ResultData<Dept> getDeptInfoByField(@RequestBody Map map) {
        // 调用 iqyService 中的 selectDeptInfoByField 方法，得到查询结果
        List<Dept> deptList = iqyService.selectDeptInfoByField(map);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明查询成功，使用系统消息
            return getSuccess(deptList);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询部门信息，根据主键id查询部门的信息
     * @date 2020/5/31
     * @param [deptId]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @GetMapping("/getDeptByDeptId")
    public ResultData<Dept> getDeptByDeptId(Integer deptId) {
        // 调用 iqyService 中的 selectDeptByDeptId 方法，得到查询结果
        Dept dept = iqyService.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if (dept != null) {
            // 说明查询成功，使用系统消息
            return getSuccess(dept);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  新增部门信息
     * @date 2020/5/31
     * @param [dept]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @PostMapping("/addDept")
    public ResultData<Dept> addDept(Dept dept) {
        // 调用 iqyService 中的 insertDept 方法，得到添加结果
        Boolean aBoolean = iqyService.insertDept(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明新增成功，使用系统消息
            return addSuccess();
        }else {
            // 新增失败，使用系统消息
            return addFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  通过主键 执行删除操作
     * @date 2020/5/31
     * @param [dept]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    public ResultData<Dept> deleteDeptByPrimaryKey(Dept dept) {
        // 调用 iqyService 中的 deleteDeptByPrimaryKey 方法，得到删除结果
        Boolean aBoolean = iqyService.deleteDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明删除成功，使用系统消息
            return deleteSuccess();
        }else {
            // 删除失败，使用系统消息
            return deleteFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  批量删除 调用父类的批量删除方法（根据主键），执行删除操作
     * @date 2020/5/31
     * @param [ids]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    public ResultData<Dept> batchDeleteByPrimaryKey(List<Object> ids) {
        // 调用 iqyService 中的 batchDeleteByPrimaryKey 方法，得到删除结果
        Boolean aBoolean = iqyService.batchDeleteByPrimaryKey(ids);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明删除成功，使用系统消息
            return deleteSuccess();
        }else {
            // 删除失败，使用系统消息
            return deleteFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  修改，通过主键-修改部门信息
     * @date 2020/5/31
     * @param [dept]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Dept>
     */
    @PostMapping("/updateDeptByPrimaryKey")
    public ResultData<Dept> updateDeptByPrimaryKey(Dept dept) {
        // 调用 iqyService 中的 batchDeleteByPrimaryKey 方法，得到修改结果
        Boolean aBoolean = iqyService.updateDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明删除成功，使用系统消息
            return updateSuccess();
        }else {
            // 删除失败，使用系统消息
            return updateFalse();
        }
    }


}
