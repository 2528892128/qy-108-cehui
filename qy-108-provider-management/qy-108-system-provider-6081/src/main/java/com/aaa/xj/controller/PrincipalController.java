package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Principal;
import com.aaa.xj.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-20-2020/5/20 15:42
 */
@RestController
public class PrincipalController extends BaseController {

    @Autowired
    private PrincipalService principalService;

    /**
     * @Description: 获取负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/20 16:11
     */
    @PostMapping("/qureyPrincipal")
    public List<Principal> qureyOne(Long id){
        List<Principal> principals = principalService.qureyOne(id);
        if (null != principals){
            return principals;
        }
        return null;
    }

    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/21 19:26
     */
    @PostMapping("/updateList")
    public Boolean updateList(@RequestBody Principal principal){
        return principalService.updateList(principal);
    }


}
