package com.aaa.xj.service;

        import com.aaa.xj.base.ResultData;
        import com.aaa.xj.model.*;
        import com.aaa.xj.vo.MenuVo;
        import com.aaa.xj.vo.TokenVo;
        import com.github.pagehelper.PageInfo;
        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

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
     * @author Seven Lee
     * @description
     *      ftp文件上传
     *      这个时候如果你自己测试过，你会发现file是无论如何都无法发送到provider项目中
     *      因为feign默认只能发送普通类型(java8种基本类型，封装类型，集合...)
     *      最终这些普通类型都可以转换为二进制流的形式在http之间传输，但是文件类型不行，
     *      因为文件类型只能转换为字节流/字符流
     *      也就是说，最终我可以让PostMapping去接收Multipart/form-data类型
     *      让feign使用json的数据格式来进行接收
     * @param [file]
     * @date 2020/5/29
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file);

    /**
     * @author ligen
     * @description 项目汇交
     *  查询所有未提交的汇交成果
     *      汇交成果状态 results_status=3
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResult")
    PageInfo<MappingProject> selectAllProjectResult(@RequestParam("pageNo") Integer pageNo,
                                                    @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有的 项目汇交信息，进行分页
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/31
     * @param [projectType, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultByType")
    PageInfo<MappingProject> selectAllProjectResultByType(@RequestParam("projectType") String projectType,
                                                          @RequestParam("pageNo") Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目汇交-操作
     *  修改汇交成果状态 results_status=2
     *  场景：点击按钮提交汇交成果项目
     * @date 2020/6/2
     * @param [id]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectResultStatusById")
    Boolean updateProjectResultStatusById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description 项目汇交-查看汇交项目审核日志
     *  查询 该项目的审核记录
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=4，成果汇交审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditProjectResult")
    PageInfo<Audit> selectAuditProjectResult(@RequestParam("refId") Long refId,
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
    PageInfo selectAllPros(@RequestBody ManProject manProject , @RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

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
     * @Author:  xj
     * @description
     *      根据类型查询项目
     * @Data: 2020/5/21
     * @param [manProject, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("selectAllProjectResultByType")
    PageInfo selectAllProjectResultByType(@RequestBody ManProject manProject,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Author:  xj
     * @description
     *      根据id删除项目信息
     * @Data: 2020/6/3
     * @param [id]
     * @Return:java.lang.Boolean
     */
    @DeleteMapping("deleteMappingProjectById")
    Boolean deleteMappingProjectById(@RequestParam("id") Long id);


    /**
     * @Author:  xj
     * @description
     * 查询测绘不同类别及是否完成的数量，用于统计图
     * @Data: 2020/6/3
     * @param []
     * @Return:java.lang.Boolean
     */
    @GetMapping("selectProjectByType")
    List<Map> selectProjectByType();

    /**
     * @Author:  xj
     * @description
     *      单位资质统计
     * @Data: 2020/6/4
     * @param []
     * @Return:java.util.List<java.util.Map>
     */
    @GetMapping("selectMappingUnitByLevel")
    List<Map> selectMappingUnitByLevel();

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
     * @Description: 根据userId分页查询负责人信息
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/31 9:50
     */
    @PostMapping("/queryPrincipalByPage")
    public PageInfo selectPrincipalByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Description: 通过userId删除负责人信息
     * @Param: [userId]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/30 22:40
     */
    @PostMapping("/deletePrincipal")
    Integer deletePrincipal(@RequestParam("userId") Long userId);

    /**
     * @Description: 根据主键id查看负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/5/31 21:05
     */
    @PostMapping("/queryOnePrincipal")
    public List<Principal> selectOnePrincipal(@RequestParam("id") Long id);

    /**
     * @Description: 新增负责人信息
     * @Param: [principal, multipartFile]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 19:41
     */
    @PostMapping("/insertPrincipal")
    Integer insertPrincipal(@RequestBody Principal principal);


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
     * @Description: 通过主键批量删除字典信息
     * @Param: [dictIds]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:14
     */
    @PostMapping("/deleteDict")
    Integer deleteDict(@RequestParam("dictIds") List<Object> dictIds);

    /**
     * @Description: 查询出要修改的字典数据
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
     * @Description: 查询要修改的负责人信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Principal>
     * @Author: ygy
     * @Date: 2020/6/1 17:16
     */
    @PostMapping("/queryupdatePrincipal")
    List<Principal> selectUpdatePrincipal(@RequestParam("id") Long id );


    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:38
     */
    @PostMapping("/updatePrincipal")
    Integer updatePrincipal(@RequestBody Principal principal);

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
     * @Description: 技术人员根据userid分页查询
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/30 23:16
     */
    @PostMapping("/queryTechnicistByPage")
    PageInfo selectTechnicistByPage(@RequestParam("userId") Long userId,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Description:根据主键id进行删除技术人员信息
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/31 10:18
     */
    @PostMapping("/deleteTechnicistKey")
    Integer deleteTechnicistKey(@RequestParam("id") Long id);

    /**
     * @Description: 根据主键id查看技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/5/31 21:58
     */
    @PostMapping("/queryOneTechnicist")
    List<Technicist> selectOneTechnicist(@RequestParam("id") Long id);

    /**
     * @Description: 添加技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 20:23
     */
    @PostMapping("/insertTechnicist")
    Integer insertTechnicist(@RequestBody Technicist technicist);

    /**
     * @Description: 查询要修改的技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/6/1 21:57
     */
    @PostMapping("/queryUpdateTechnicist")
    List<Technicist> selectUpdateTechnicist(@RequestParam("id") Long id);


    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Boolean
     * @Author: ygy
     * @Date: 2020/5/22 16:37
     */
    @PostMapping("/updateTechnicist")
    Integer updateTechnicist(@RequestBody Technicist technicist);

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
     * @Description: 查询要修改的测绘单位信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Mapping_unit>
     * @Author: ygy
     * @Date: 2020/5/29 22:13
     */
    @PostMapping("/queryUpdateMappingUnit")
    List<Mapping_unit> selectUpdateMappingUnit(@RequestParam("id") Long id);

    /**
     * @Description: 新增测绘项目信息
     * @Param: [manProject]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/4 11:50
     */
    @PostMapping("/addManprodect")
    Integer addManProject(@RequestBody ManProject manProject);


    /**
     * @Description: 修改测绘单位信息
     * @Param: [mappingUnit]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/29 23:14
     */
    @PostMapping("/updateMappingUnit")
    Integer updateMappingUnit(@RequestBody Mapping_unit mappingUnit);


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
     *      使用动态sql实现条件查询
     * @Data: 2020/5/31
     * @param [username, deptId, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectUserByField")
    PageInfo<User> selectUserByField(@RequestBody Map map, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);
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
     * @description 系统主页-测绘单位
     *  查询测绘单位名称
     *      模糊查询-动态sql
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
     * @description 系统主页-测绘单位
     *  查询测绘单位基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.Mapping_unit
     */
    @GetMapping("/selectUnitInfoById")
    Mapping_unit selectUnitInfoById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description 系统主页-测绘单位-查看详情-项目信息
     *  查询，
     *      根据单位id 查询该单位下的项目信息，进行分页
     * @date 2020/5/31
     * @param [id, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/queryProjectForUnitByUserId")
    PageInfo<MappingProject> queryProjectForUnitByUserId(@RequestParam("id") Long id,
                                                         @RequestParam("pageNo") Integer pageNo,
                                                         @RequestParam("pageSize") Integer pageSize);

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
     * @description 系统主页-测绘项目
     *  查询项目基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectInfoById")
    MappingProject selectProjectInfoById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description 系统主页-测绘项目-查看详情
     *  查询测绘项目详情-根据主键id查询
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectDetailById")
    MappingProject selectProjectDetailById(@RequestParam("id") Long id);

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
     * @description 系统主页-测绘成果
     *  查询测绘成果基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    @GetMapping("/selectResultInfoById")
    ResultCommit selectResultInfoById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description 系统主页-测绘成果-查看详情
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

    /**
     * liukai
     *根据ID查询单条仪器设备信息
     */
    @PostMapping("/OneEquipment")
    List<Equipment> selectOneEquipment(@RequestParam("id") Long id);


    /**
     * liukai
     * 根据实体新增仪器设备信息
     * @param equipment
     * @return
     */
    @PostMapping("/insertEquipment")
    Integer insertEquipment(@RequestBody Equipment equipment);

    /**
     * liukai
     * 先id查询再修改仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/selectByKey")
    List<Equipment> selectByKey(@RequestParam("id") Long id);
    @PostMapping("/updateEquipment")
    Integer updateEquipment(@RequestBody Equipment equipment);

    /**
     * liukai
     * 根据id删除仪器设备信息
     * @param id
     * @return
     */
    @PostMapping("/deleteByKey")
    Integer deleteByKey(@RequestParam("id") Long id);


    /**
     * liukai
     * 根据id查询单个特殊岗位人员
     * @param id
     * @return
     */
    @PostMapping("/selectOneSpecialPost")
    List<SpecialPost> selectOneSpecialPost(@RequestParam("id") Long id);



    /**
     * liukai
     * 根据实体新增特殊岗位人员信息
     * @param specialPost
     * @return
     */
    @PostMapping("/insertSpecialPost")
    Integer insertSpecialPost(@RequestBody SpecialPost specialPost );


    /**
     * liukai
     * 先进行id先查询再修改特殊岗位人员信息
     * @param id
     * @return
     */
    @PostMapping("/selectByKeySpecialPost")
    List<SpecialPost> selectByKeySpecialPost(@RequestParam("id") Long id);
    @PostMapping("/updateSpecialPost")
    Integer updateSpecialPost(@RequestBody SpecialPost specialPost);


    /**
     * liukai
     * 根据id进行删除特殊岗位人员信息
     * @param id
     * @return
     */
    @PostMapping("/deleteSpecialPost")
    Integer deleteSpecialPost(@RequestParam("id") Long id);


    /**
     *liukai
     * 分页查询仪器设备信息
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectEquipmentByPage")
    PageInfo<Equipment> selectEquipmentByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize);


    /**
     * liukai
     * 分页查询特殊岗位人员信息
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectSpecialPostByPage")
    PageInfo<SpecialPost> selectSpecialPostByPage(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize")Integer pageSize);

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
    List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Long parentId);

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
    List<Dept> selectDeptInfoByField(@RequestBody Map map);

    /**
     * @author ligen
     * @description 部门管理
     *  查询部门信息，根据主键id查询部门的信息
     * @date 2020/5/31
     * @param [parentId]
     * @return java.util.List<com.aaa.xj.model.Dept>
     */
    @GetMapping("/selectDeptByDeptId")
    Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId);

    /**
     * @author ligen
     * @description
     *  新增部门信息
     * @date 2020/5/31
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/insertDept")
    Boolean insertDept(@RequestBody Dept dept);

    /**
     * @author ligen
     * @description
     *  通过主键 执行删除操作
     * @date 2020/5/31
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    Boolean deleteDeptByPrimaryKey(@RequestBody Dept dept);

    /**
     * @author ligen
     * @description
     *  批量删除 调用父类的批量删除方法（根据主键），执行删除操作
     * @date 2020/5/31
     * @param [ids]
     * @return java.lang.Boolean
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    Boolean batchDeleteByPrimaryKey(@RequestBody List<Object> ids);

    /**
     * @author ligen
     * @description
     *  修改，通过主键-修改部门信息
     * @date 2020/5/31
     * @param [dept]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateDeptByPrimaryKey")
    Boolean updateDeptByPrimaryKey(@RequestBody Dept dept);

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/1
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectAudit")
    PageInfo<MappingProject> selectAllProjectAudit(@RequestParam("pageNo") Integer pageNo,
                                                   @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/1
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectAuditByType")
    PageInfo<MappingProject> fuzzyProjectAuditByType(@RequestParam("projectName") String projectName,
                                                     @RequestParam("pageNo") Integer pageNo,
                                                     @RequestParam("pageSize") Integer pageSize);


    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询项目详细信息-主键查询
     * @date 2020/6/1
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectAuditById")
    MappingProject selectProjectAuditById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description
     *  查询 该项目的审核记录，分页
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=2，项目等级审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditProjectByRefId")
    PageInfo<Audit> selectAuditProjectByRefId(@RequestParam("refId") Long refId,
                                              @RequestParam("pageNo") Integer pageNo,
                                              @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultAudit")
    PageInfo<MappingProject> selectAllProjectResultAudit(@RequestParam("pageNo") Integer pageNo,
                                                         @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultAuditByType")
    PageInfo<MappingProject> fuzzyProjectResultAuditByType(@RequestParam("projectName") String projectName,
                                                           @RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息-查看
     *  查看汇交成果项目详情
     * @date 2020/6/2
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectResultAuditById")
    MappingProject selectProjectResultAuditById(@RequestParam("id") Long id);

    /**
     * @author ligen
     * @description 项目审核-项目审核
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectToAudit")
    PageInfo<MappingProject> selectAllProjectToAudit(@RequestParam("pageNo") Integer pageNo,
                                                     @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-项目审核-条件查询
     *  条件查询-模糊查询，项目名称
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectToAuditByPName")
    PageInfo<MappingProject> fuzzyProjectToAuditByPName(@RequestParam("projectName") String projectName,
                                                        @RequestParam("pageNo") Integer pageNo,
                                                        @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-项目审核-审核
     *  更改项目审核结果 audit_status
     *      0 通过；1 不通过。
     *  并 添加审核日志 memo审核意见，status==audit_status审核状态
     * @date 2020/6/3
     * @param [audit, id, auditStatus]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectAuditStatus")
    Boolean updateProjectAuditStatus(@RequestBody Audit audit,
                                     @RequestParam("id") Long id,
                                     @RequestParam("auditStatus") Integer auditStatus);

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultToAudit")
    PageInfo<MappingProject> selectAllProjectResultToAudit(@RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-条件模糊查询
     *  条件查询-模糊查询，项目名称 projectName
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultToAuditByPName")
    PageInfo<MappingProject> fuzzyProjectResultToAuditByPName(@RequestParam("projectName") String projectName,
                                                              @RequestParam("pageNo") Integer pageNo,
                                                              @RequestParam("pageSize") Integer pageSize);

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-审核
     *      t_mapping_project：
     *          id 操作项目编号id；
     *          results_status 项目审核结果，0 通过；1 不通过；
     *      操作成功，新增审核日志：memo审核意见，status==audit_status审核状态
     * @date 2020/6/3
     * @param [audit, id, auditStatus]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectResultStatus")
    Boolean updateProjectResultStatus(@RequestBody Audit audit,
                                      @RequestParam("id") Long id,
                                      @RequestParam("resultsStatus") Integer resultsStatus);


    /**
     * @Author:  xj
     * @description
     * 查询所有角色信息
     * @Data: 2020/6/2
     * @param [pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectAllRole")
    PageInfo selectAllRole(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Author:  xj
     * @description
     *      根据条件查询
     * @Data: 2020/6/1
     * @param [map, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectRoleByField")
    PageInfo selectRoleByField(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);


    /**
     * @Author:  xj
     * @description
     *       根据主键查询角色信息
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:com.aaa.xj.model.Role
     */
    @GetMapping("selectRoleByPrimaryKey")
    Role selectRoleByPrimaryKey(@RequestParam("roleId") Long roleId);

    /**
     * @Author:  xj
     * @description
     *      根据角色id查询拥有的权限
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:java.util.List<com.aaa.xj.model.RoleMenu>
     */
    @GetMapping("/selectRoleMenuById")
    List<RoleMenu> selectRoleMenuById(@RequestParam("roleId") Long roleId);

    /**
     * @Author:  xj
     * @description
     *      新增角色和权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.lang.Boolean
     */
    @PutMapping("insertRole")
    Boolean insertRole(@RequestBody Map map);


    /**
     * @Author:  xj
     * @description
     *      修改角色信息和权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.lang.Boolean
     */
    @PostMapping("updateRoleByPrimaryKey")
    Boolean updateRoleByPrimaryKey(@RequestBody Map map);

    /**
     * @Author:  xj
     * @description
     *      根据id批量删除角色和权限
     * @Data: 2020/6/2
     * @param [roleIds]
     * @Return:java.lang.Boolean
     */
    @DeleteMapping("/deleteRoleAndMenu")
    Boolean deleteRoleAndMenuByRoleId(@RequestBody List<Object> roleIds);

    /**
     * @Author:  xj
     * @description
     *      根据条件查询菜单信息
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.util.List<com.aaa.xj.model.Menu>
     */
    @PostMapping("selectMenuByField")
    List<Menu> selectMenuByField(@RequestBody Map map);

    /**
     * @Author:  xj
     * @description
     *      根据主键查询菜单信息
     * @Data: 2020/6/3
     * @param [menuId]
     * @Return:com.aaa.xj.model.Menu
     */
    @GetMapping("selectMenuByPrimaryKey")
    Menu selectMenuByPrimaryKey(@RequestParam("menuId") Long menuId);

    /**
     * @Author:  xj
     * @description
     *      遍历查询所有菜单信息
     * @Data: 2020/6/3
     * @param [parentId]
     * @Return:java.util.List<com.aaa.xj.vo.MenuVo>
     */
    @GetMapping("selectMenuByParentId")
    List<MenuVo> selectMenuByParentId(@RequestParam("parentId") Object parentId);

    /**
     * @Author:  xj
     * @description
     *      新增菜单
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Boolean
     */
    @PostMapping("insertMenu")
    Boolean insertMenu(@RequestBody Menu menu);


    /**
     * @Author:  xj
     * @description
     *      新增按钮
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Boolean
     */
    @PostMapping("insertMenuButton")
    Boolean insertMenuButton(@RequestBody Menu menu);

    /**
     * @Author:  xj
     * @description
     *      根据id批量删除菜单
     * @Data: 2020/6/3
     * @param [menuIds]
     * @Return:java.lang.Boolean
     */
    @PostMapping("deleteMenuByMenuId")
    Boolean deleteMenuByMenuId(@RequestBody List<Object> menuIds);


    /**
     * @Author:  xj
     * @description
     * 根据主键id 更新菜单信息
     * @Data: 2020/6/3
     * @param [menu]
     * @Return:java.lang.Integer
     */
    @PostMapping("updateMenuByPrimaryKey")
    Integer updateMenuByPrimaryKey(@RequestBody Menu menu);
}


