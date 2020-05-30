package com.aaa.xj.controller;

import com.aaa.xj.model.Dict;
import com.aaa.xj.service.DictService;
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
 * @create: 2020-05-26-2020/5/26 16:42
 */
@RestController
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * @Description: 查询字典信息
     * @Param: []
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/26 16:43
     */
    @PostMapping("/queryDict")
    public List<Dict> selectDict(){
        try{
            List<Dict> dicts = dictService.selectDict();
            return dicts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 新增字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 17:39
     */
    @PostMapping("/addDict")
    public Integer addDict(@RequestBody Dict dict){
        return dictService.addDict(dict);
    }

    /**
     * @Description: 通过主键批量删除字典信息
     * @Param: [dictIds]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:12
     */
    @PostMapping("/deleteDict")
    public Integer deleteDict(@RequestParam("dictIds") List<Object> dictIds){
        return dictService.deleteDict(dictIds);
    }

    /**
     * @Description: 查询出要修改的数据
     * @Param: [dict]
     * @return: com.aaa.xj.model.Dict
     * @Author: ygy
     * @Date: 2020/5/28 22:09
     */
    @PostMapping("/queryUpdateDict")
    public Dict selectUpdateDict(@RequestParam("dictId") Long dictId){
        return dictService.selectUpdateDict(dictId);
    }

    /**
     * @Description: 修改字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 23:15
     */
    @PostMapping("/updateDict")
    public Integer updateDict(@RequestBody Dict dict){
        return dictService.updateDict(dict);
    }


    /**
     * @Description: 字典信息分页查询
     * @Param: [dict, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/26 19:56
     */
    @PostMapping("/queryDictAllPage")
    public PageInfo<Dict> selectAllDictByPage(@RequestBody Dict dict, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try{
            PageInfo pageInfo = dictService.selectAllDictByPage(dict, pageNo, pageSize);
            return pageInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    /**
     * @Description: 字典信息条件查询
     * @Param: [dict]
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/26 18:04
     */
    @PostMapping("/queryDictList")
    public List<Dict> selectDictList(@RequestBody Dict dict){
        try {
            List<Dict> dicts = dictService.queryList(dict);
            return dicts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
