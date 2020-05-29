package com.aaa.xj.service;

import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.*;
import com.aaa.xj.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/15 16:14
 * @Description
 *      fallbackFactory:就是来实现熔断的，在实际开发中，开发阶段不能去开启熔断
 *      因为一旦开启了熔断，整个异常都不会再抛出，不方便调bug
 *
 *      实际开发必须注意的东西:
 *          无论是springcloud1.x还是2.x版本
 *          一旦使用feign来进行传递参数的时候，必须要注意两个点:
 *              1.如果是简单类型(8种基本类型，String)--->必须使用注解@RequestParam
 *                  基本类型可以传多个，也就是说一个方法的参数中可以使用多@RequestParam
 *
 *              2.如果传递包装类型(List, Map, Vo, Po)，只能传递一个，而且必须要使用@RequestBody注解
 *
 *         也就是说最终把这些参数值传递到provider项目的controller中，在这provider项目的controller中也必须使用
 *         相同的注解，而且provider和api的方法必须要一模一样(copy是最方便的)
 *
 **/
@FeignClient(value ="system-interface")
public interface IQYService {

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/5/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *     实现登陆日志
     * @Data: 2020/5/27
     * @param [map]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/addLoginLog")
    ResultData addLoginLog(@RequestBody Map map);

    /**
     * @author ligen
     * @description
     *  查询所有的，已提交的项目信息
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAll")
    List<MappingProject> selectAllProject();

    /**
     * @author ligen
     * @description
     *  条件查询，根据项目类型 projectType，查询所有的 已提交的项目信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param projectType
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllByType")
    List<MappingProject> selectAllByType(@RequestParam("projectType") String projectType);

    /**
     * @author ligen
     * @description
     *  分页查询 ，将查询的所有已提交项目信息，进行分页
     *      参数：pageNo 当前页数，pageSize 每页数据个数
     * @date 2020/5/23
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
     @PostMapping("/selectALLByPage")
     PageInfo selectALLByPage(@RequestBody MappingProject mappingProject,
                              @RequestParam("pageNo") Integer pageNo,
                              @RequestParam("pageSize") Integer pageSize);

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
    List<ManProject> selectAllPros(@RequestBody ManProject manProject);

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
    ManProject selectById(@RequestParam("id") Long id);

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
    Integer updateById(@RequestBody ManProject manProject);

    /**
     * @Description: 获取负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/20 16:13
     */
    @PostMapping("/qureyPrincipal")
    List<Principal> qureyOne(@RequestParam("userId") Long userId);

    /**
     * @Description: 新增字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 17:41
     */
    @PostMapping("/addDict")
    Integer addDict(@RequestBody Dict dict);

    /**
     * @Description: 删除字典信息
     * @Param: [dictIds]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:14
     */
    @PostMapping("/deleteDict")
    Integer deleteDict(@RequestParam("dictIds") List<Object> dictIds);

    /**
     * @Description: 查询出要修改的数据
     * @Param: [dict]
     * @return: com.aaa.xj.model.Dict
     * @Author: ygy
     * @Date: 2020/5/28 22:16
     */
    @PostMapping("/queryUpdateDict")
    Dict selectUpdateDict(@RequestParam("dictId") Long dictId);

    /**
     * @Description: 修改字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 23:16
     */
    @PostMapping("/updateDict")
    Integer updateDict(@RequestBody Dict dict);

    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:38
     */
    @PostMapping("/updateList")
    Boolean updateList(@RequestBody Principal principal);

    /**
     * @Description: 获取技术人员信息
     * @Param: [userId]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/22 10:30
     */
    @PostMapping("/qureyTechnicist")
    List<Technicist> qureyTechnicist(@RequestParam("userId") Long userId);

    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Boolean
     * @Author: ygy
     * @Date: 2020/5/22 16:37
     */
    @PostMapping("/updateTechnicist")
    Boolean updateTechnicist(@RequestBody Technicist technicist);

    /**
     * @Description: 获取单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/5/22 20:25
     */
    @PostMapping("/qureyMapping_unit")
    List<Mapping_unit> qureyMapping_unit(@RequestParam("userId") Long userId);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询所有用户信息
     * @Data: 2020/5/26
     * @param
     * @Return:java.util.List<com.aaa.xj.model.User>
     */
    @PostMapping("/selectAllUser")
    PageInfo selectAllUser(@RequestBody User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      用户新增
     * @Data: 2020/5/26
     * @param user
     * @Return:java.lang.Boolean
     */
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据主键删除用户
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/deleteUser")
    Integer deleteUser(@RequestBody User user);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id批量删除用户
     * @Data: 2020/5/26
     * @param [ids]
     * @Return:java.lang.Integer
     */
    @PostMapping("/delectMoreUser")
    Integer deleteMoreUser(@RequestBody List<Object> ids);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id查询user信息
     * @Data: 2020/5/26
     * @param [id]
     * @Return:com.aaa.xj.model.User
     */
    @GetMapping("/selectUserById")
    User selectUserById(@RequestParam("id") Long id);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id修改用户信息
     * @Data: 2020/5/28
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/updateUserById")
    Integer updateUserById(@RequestBody User user);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据用户性别查询用户信息
     * @Data: 2020/5/25
     * @param [ssex, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectUserBySsex")
    PageInfo selectUserBySsex(@RequestParam("ssex") String ssex,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据用户状态查询用户信息
     * @Data: 2020/5/25
     * @param [ssex, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectUserBySta")
    PageInfo selectUserBySta(@RequestParam("status") String status,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      重置密码
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/resetUserPwd")
    Integer ResetUserPwd(@RequestBody User user);

    /**
     * @Description: 根据userID获取仪器设别信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Equipment>
     * @Author: ygy
     * @Date: 2020/5/25 22:06
     */
    @PostMapping("/qureyEquipment")
    List<Equipment> selectEquipment(@RequestParam("userId") Long userId);

    /**
     * @Description: 根据userId获取特殊人员信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.SpecialPost>
     * @Author: ygy
     * @Date: 2020/5/25 22:07
     */
    @PostMapping("/qureySpecialPost")
    List<SpecialPost> selectSpecialPost(@RequestParam("userId") Long userId);

    /**
     * @author ligen
     * @description
     *  根据 业务编号 ref_id，查询该业务的审核日志
     * @date 2020/5/25
     * @param [refId]
     * @return java.util.List<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditByRefId")
    List<Audit> selectAuditByRefId(@RequestParam("refId") Long refId);

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘单位名称
     * @date 2020/5/27
     * @param [mappingUnit]
     * @return java.util.List<com.aaa.xj.model.Mapping_unit>
     */
    @GetMapping("/fuzzyUnitName")
    List<Mapping_unit> fuzzyUnitName(@RequestParam("unitName") String unitName,
                                     @RequestParam("ownedDistrict") String ownedDistrict,
                                     @RequestParam("qualificationLevel") String qualificationLevel);

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘项目名称
     * @date 2020/5/27
     * @param [projectName, projectType, startDate]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectName")
    List<MappingProject> fuzzyProjectName(@RequestParam("projectName") String projectName,
                                          @RequestParam("projectType") String projectType,
                                          @RequestParam("startDate") String startDate);

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘成果名称
     * @date 2020/5/27
     * @param [name, projectType, resultDate]
     * @return java.util.List<com.aaa.xj.model.ResultCommit>
     */
    @GetMapping("/fuzzyResultName")
    List<ResultCommit> fuzzyResultName(@RequestParam("name") String name,
                                       @RequestParam("projectType") String projectType,
                                       @RequestParam("resultDate") String resultDate);

    /**
     * @author ligen
     * @description
     *  查询测绘成果详情，根据成果主键id
     * @date 2020/5/27
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    @GetMapping("/selectResultDetails")
    ResultCommit selectResultDetails(@RequestParam("id") Long id);

    /**
     * @Description:获取字典信息
     * @Param: []
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/27 13:55
     */
    @PostMapping("/queryDict")
    List<Dict> selectDict();

    /**
     * @Description: 字典信息分页查询
     * @Param: [dict, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/27 13:56
     */
    @PostMapping("/queryDictAllPage")
    PageInfo<Dict> selectAllDictByPage(@RequestBody Dict dict, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    /**
     * @Description: 字典信息条件查询
     * @Param: [dict]
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/27 13:57
     */
    @PostMapping("/queryDictList")
    List<Dict> selectDictList(@RequestBody Dict dict);
}



