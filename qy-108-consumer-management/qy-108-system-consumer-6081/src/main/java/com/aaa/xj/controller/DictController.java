package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Dict;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-26-2020/5/26 16:47
 */
@RestController
public class DictController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Description: 获取字典信息
     * @Param: []
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/26 16:49
     */
    @PostMapping("/queryDict")
    public ResultData selectDict(){
        try {
            List<Dict> dicts = iqyService.selectDict();
            return getSuccess(dicts);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getFalse();
    }

    /**
     * @Description: 字典信息分页查询
     * @Param: [dict, pageNo, pageSize]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/26 20:00
     */
    @PostMapping("/queryDictAllPage")
    public ResultData selectAllDictByPage(Dict dict,Integer pageNo,Integer pageSize){
        try{
            PageInfo<Dict> dictPageInfo = iqyService.selectAllDictByPage(dict, pageNo, pageSize);
            return getSuccess(dictPageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getFalse();
    }

    /**
     * @Description: 字典信息条件查询
     * @Param: [dict]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/26 18:39
     */
    @PostMapping("/queryDictList")
    public ResultData selectDictList(@RequestBody Dict dict){
        List<Dict> dicts = iqyService.selectDictList(dict);
        if (null != dict){
            return getSuccess(dicts);
        }
        return getFalse();
    }
}
