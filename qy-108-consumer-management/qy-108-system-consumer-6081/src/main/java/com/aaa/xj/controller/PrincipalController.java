package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Principal;
import com.aaa.xj.service.IQYService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultData QureyList(@RequestParam("userId") Long id) {

        List<Principal> principals = qyService.qureyOne(id);
        if (null != principals) {
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
    @PostMapping("/updateList")
    public ResultData updateList(@RequestBody Principal principal){
        //判断返回的Boolean
        if (qyService.updateList(principal)){
            //如果为true返回成功信息
            return updateSuccess();
        }
        //如果为false返回失败信息
        return updateFalse();
    }

}