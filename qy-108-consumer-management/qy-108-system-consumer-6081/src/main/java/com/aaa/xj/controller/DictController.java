package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Dict;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
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
     * @Description: 新增字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 17:41
     */
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict){
        //执行新增字典操作
        Integer integer = iqyService.addDict(dict);
        //判断新增字典信息的受影响行数
        if (integer > 0){
            //大于0说明新增成功返回统一系统信息
            return addSuccess();
        }
        //不大于0说明新增失败返回统一系统信息
        return addFalse();
    }

    /**
     * @Description: 通过主键批量删除字典信息
     * @Param: [dictIds]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:14
     */
    @PostMapping("/deleteDict")
    public ResultData deleteDict(@RequestParam("dictIds") List<Object> dictIds){
        Integer integer = iqyService.deleteDict(dictIds);
        //判断删除的受影响行数是否大于0
        if (integer > 0){
            //大于0删除成功返回自定义信息
            return deleteSuccess();
        }
        //不大于0删除失败返回自定义信息
        return deleteFalse();
    }

    /** 
     * @Description: 查询要修改的信息
     * @Param: [dict]
     * @return: com.aaa.xj.base.ResultData 
     * @Author: ygy
     * @Date: 2020/5/28 22:34 
     */
    @PostMapping("/queryUpdateDict")
    public ResultData selectUpdateDict(@RequestParam("dictId") Long dictId){
        Dict dict1 = iqyService.selectUpdateDict(dictId);
        //判断查询结果是否为空
        if (null != dict1 && !"".equals(dict1)){
            //不为空返回自定义信息
            return getSuccess(dict1);
        }
        //为空返回自定义信息
        return getFalse();
    }

    /**
     * @Description: 修改字典信息
     * @Param: [dict]
     * @return: com.aaa.xj.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/28 23:18
     */
    @PostMapping("/updateDict")
    public ResultData updateDict(@RequestBody Dict dict){
        Integer integer = iqyService.updateDict(dict);
        //判断修改受影响的行数
        if (integer>0){
            //如果大于0返回自定义信息
            return updateSuccess();
        }
        //如果不大于0返回自定义信息
        return updateFalse();
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
