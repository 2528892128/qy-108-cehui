package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.service.IQYService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户管理" ,tags = "用户管理接口")
public class UserController extends BaseController {

    @Autowired
    private IQYService iqyService;
}
