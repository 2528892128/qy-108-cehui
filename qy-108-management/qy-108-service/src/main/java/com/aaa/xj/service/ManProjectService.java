package com.aaa.xj.service;

import com.aaa.xj.base.BaseModel;
import com.aaa.xj.base.BaseService;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.mapper.ManProjectMapper;
import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.User;
import com.aaa.xj.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.aaa.xj.status.CrudStatus.*;

@Service
public class ManProjectService extends BaseService<ManProject> {

    @Autowired
    private ManProjectMapper manProjectMapper;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      项目查询
     * @Data: 2020/5/21 17:10
     * @param user
     * @Return:com.aaa.xj.base.ResultData
     */

    public PageInfo<ManProject> selectAllPros(ManProject manProject,Integer pageNo,Integer pageSize){

        PageHelper.startPage(pageNo,pageSize);

        try {
            //调用查询方法
            List<ManProject> manProjects = manProjectMapper.selectAllPros(manProject);
                //判断是否查询出值
                if (!"".equals(manProjects) && null != manProjects){
                    //将结果放入分页
                    PageInfo<ManProject> manProjectPageInfo = new PageInfo<>(manProjects);
                    return manProjectPageInfo;
                }
                else {
                    return null;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据主键id进行查询
     * @Data: 2020/5/21
     * @param id
     * @Return:java.util.List<com.aaa.xj.model.ManProject>
     */
    public ManProject selectById(Long id){
        try {
            if (!"".equals(id)){
                //根据id获取项目信息
                ManProject manProject = manProjectMapper.selectByPrimaryKey(id);
                //判断是否存在该项目
                if (!"".equals(manProject) && null != manProject){
                    return manProject;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *       通过id修改项目
     * @Data: 2020/5/21
     * @param manProject
     * @Return:java.lang.Integer
     */
    public Integer updateById(ManProject manProject){

        int i = 0;
        try {

            if (!"".equals(manProject)){
                //执行修改的方法 返回受影响的行数
                i = manProjectMapper.updateByPrimaryKey(manProject);
                //判断受影响的行数
                if (i>0){
                    return i;
                }else {
                    //再次执行修改操作
                    int j = manProjectMapper.updateByPrimaryKey(manProject);
                    if (j>0){
                        return j;
                    }
                }
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 新增测绘项目信息
     * @Param: [manProject]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/3 23:31
     */
    public Integer addManProject(ManProject manProject){

        if (null != manProject && !"".equals(manProject)){
            //获取时间
        Date date = new Date();
        //设置时间格式作为创建时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
            // 获取系统当前时间的毫秒数
        Long timeMillis = System.currentTimeMillis();
            // 创建Random对象
        Random random = new Random();
        // 做一个随机数，随机区间是0-9999之间随机
        Integer randomNum = random.nextInt(9999);
        //把系统当前时间和randomNum相加作为id
        Long id = randomNum + timeMillis;
            manProject.setId(id)
                    .setCreateTime(format);

            int insert = manProjectMapper.insert(manProject);

            if (insert > 0){
                return insert;
            }
            return 0;
        }
        return 0;
    }


    /**
     * @Author:  xj
     * @description
     *      根据类型查询数据
     * @Data: 2020/5/21
     * @param [manProject, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    public PageInfo selectMappingProjectByType(ManProject manProject ,Integer pageNo,Integer pageSize){
        //传入当前页和数量
        PageHelper.startPage(pageNo,pageSize);

        try {
            //调用查询方法
            List<ManProject> manProjects = manProjectMapper.selectByTypes(manProject);
            //判断是否查询出数据
            if (null !=manProject){
                //将查询的数据放入分页
                PageInfo<ManProject> manProjectPageInfo = new PageInfo<>(manProjects);
                return manProjectPageInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      根据id删除项目信息
     * @Data: 2020/6/3
     * @param [id]
     * @Return:java.lang.Boolean
     */
    public Boolean deleteMappingProjectById(Long id){
        //判断前段是否传值成功
        if (null !=id){
            try {
                //调用删除方法
                int i = manProjectMapper.deleteByPrimaryKey(id);
                if (i>0){
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     *  查询测绘不同类别及是否完成的数量，用于统计图
     * @Data: 2020/5/24
     * @param []
     * @Return:java.util.List<java.util.Map>
     */
    public List<Map> selectProjectType(){
        List<Map> maps = manProjectMapper.selectProjectType();
        //判断结果是否为空
        if (null !=maps && !maps.isEmpty()){
            //不为空 返回数据
            return maps;
        }else{
            //查询失败 返回null
            return null;
        }
    }
}
