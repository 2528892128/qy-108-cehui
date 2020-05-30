package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.service.IQYService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/29 14:22
 * @Description
 **/
@RestController
@Api(value = "文件上传", tags = "文件上传的接口")
public class UploadController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @author Seven Lee
     * @description
     *      ftp文件上传
     *      虽然咱们已经考虑了非常严谨的业务了，但是最终没有考虑到一个无状态的token
     *      也就是按照分布式项目的需求(分布式项目几乎不用shiro(shiro玩的是session，分布式项目session经常跨域)
     *      一般情况使用JWT),分布式项目使用的都是无状态token
     *      就意味着每一次请求都必须携带token，也就意味着每一次返回给前端数据的时候也必须要携带token，用户退出账号了/或者
     *      30分钟之后这个token还要及时销毁
     *      也就是说token会随着浏览器的跳转一起去跳转！！！！
     * @param [file]
     * @date 2020/5/29
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/upload")
    @ApiOperation(value = "实现文件上传", notes = "单文件上传接口")
    public ResultData uploadFile(MultipartFile file) {
        // TODO 最后有一个判断，自己完成即可
        Boolean aBoolean = iqyService.uploadFile(file);
       if (aBoolean){
           return super.uploadSuccess();
       }
       return super.uploadFalse();
    }

}
