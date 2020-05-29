package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.DictMapper;
import com.aaa.xj.model.Dict;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-26-2020/5/26 16:34
 */
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictMapper dictMapper;

    /**
     * @Description: 查询字典信息
     * @Param: []
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/26 16:42
     */
    public List<Dict> selectDict(){
        try {
            //查询所有的字典信息
            List<Dict> dicts = dictMapper.selectAll();
            //判断查询结果是否为空
            if (null != dicts && !"".equals(dicts)){
                //不为空返回查询结果
                return dicts;
            }else {
                return null;
            }
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
     * @Date: 2020/5/28 17:33
     */
    public Integer addDict(Dict dict){
        //判断新增字典信息是否为空
        if (null != dict){
            //不为空执行增加操作
            int insert = dictMapper.insert(dict);
            if (insert > 0){
                //把新增受影响的行数返回
                return insert;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }

    /**
     * @Description: 删除字典信息
     * @Param: [dictId]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:01
     */
    public Integer deleteDict(List<Object> dictIds){
        //判断参数dictIds是否大于0
        if (dictIds.size()>0){
            try {
                //大于0根据dictIds删除字典信息
                Integer integer = batchDelete(dictIds);
                //判断删除的受影响行数
                if (integer > 0){
                    //大于0返回受影响行数
                    return integer;
                }else {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            return 0;
        }
        return 0;
    }

    /**
     * @Description: 查询出要修改的数据
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 21:58
     */
    public Dict selectUpdateDict(Long dictId){
        //判断参数dict是否为空
        if (!"".equals(dictId)){
            try {
                //不为空调用父类方发进行查询一条数据操作
                Dict dict = dictMapper.selectUpdateDict(dictId);
                //判断查询结果是否为空
                if (null != dict && !"".equals(dict)){
                    //不为空返回查询结果
                    return dict;
                }else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Description: 修改字典信息
     * @Param: [dict]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/28 23:09
     */
    public Integer updateDict(Dict dict){
        //判断要修改的数据是否为空
        if (null != dict && !"".equals(dict)){
            try {
                //不为空调用父类的方法执行修改操作
                Integer update = dictMapper.updateDict(dict);
                //判断修改受影响的行数
                if (update > 0){
                    //如果大于0就返回受影响的行数
                    return update;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }



    /**
     * @Description: 字典信息分页查询
     * @Param: [dict, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/26 19:48
     */
    public PageInfo<Dict> selectAllDictByPage(Dict dict,Integer pageNo,Integer pageSize){
        try{
            //调用父类分页方法把参数穿进去查询
            PageInfo<Dict> dictPageInfo = queryListByPage(dict, pageNo, pageSize);
            //判断查询结果是否为空
            if (null != dictPageInfo && !"".equals(dictPageInfo)){
                //不为空返回查询结果
                return dictPageInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description:根据条件查询
     * @Param: [dict]
     * @return: java.util.List<com.aaa.xj.model.Dict>
     * @Author: ygy
     * @Date: 2020/5/26 18:01
     */
    @Override
    public List<Dict> queryList(Dict dict) throws Exception {
        return super.queryList(dict);
    }

}
