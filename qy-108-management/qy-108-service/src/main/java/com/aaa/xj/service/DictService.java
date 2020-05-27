package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.DictMapper;
import com.aaa.xj.model.Dict;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
