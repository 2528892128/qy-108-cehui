package com.aaa.xj.controller;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.base.CommonController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.LoginLogs;
import com.aaa.xj.service.LoginLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogController  extends CommonController<LoginLogs> {

    @Autowired
    private LoginLogsService loginLogsService;

    @Override
    public BaseService<LoginLogs> getBaseService() {
        return loginLogsService;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      实现登录日志
     * @Data: 2020/5/27
     * @param [map]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/addLoginLog")
    public ResultData addLoginLog(@RequestBody Map map){
        return super.add(map);
    }
}
