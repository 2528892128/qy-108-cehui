package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.TechnicistMapper;
import com.aaa.xj.model.Technicist;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-22-2020/5/22 10:20
 */
@Service
public class TechnicistService extends BaseService<Technicist> {

    @Autowired
    private TechnicistMapper technicistMapper;

    /**
     * @Description: 获取技术人员信息
     * @Param: [userId]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/5/22 10:24
     */
    public List<Technicist> qureyTechnicist(Long userId){
        //根据userid去查询技术人员信息
        List<Technicist> technicist = technicistMapper.qureyTechnicist(userId);
        if (null != technicist){
            //不为空返回信息
            return technicist;
        }
        return null;
    }

    /**
     * @Description: 技术人员根据userid分页查询
     * @Param: [userId, pageNo, pageSize]
     * @return: com.github.pagehelper.PageInfo
     * @Author: ygy
     * @Date: 2020/5/30 23:04
     */
    public PageInfo selectTechnicistByPage(Long userId,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        try{
            //根据userId进行查询操作
            List<Technicist> technicists = technicistMapper.qureyTechnicist(userId);
            //判断查询的结果是否为空
            if (null != technicists && !"".equals(technicists)){
                //不为空把查询的结果进行分页
                PageInfo<Technicist> technicistPageInfo = new PageInfo<Technicist>(technicists);
                //把分页的数据返回
                return technicistPageInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:根据主键id删除技术人员信息
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/5/31 10:07
     */
    public Integer deleteTechnicistKey(Long id){
        //先判断前端传过来的主键id是否为空
        if (null != id && !"".equals(id)){
            //不为空进行根据主键id删除操作
            Integer integer = technicistMapper.deleteTechnicistKey(id);
            //判断删除后受影响的行数
            if (integer > 0){
                //大于0说明删除成功返回受影响的行数
                return integer;
            }else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * @Description: 根据主键id查看技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/5/31 21:55
     */
    public List<Technicist> selectOneTechnicist(Long id){
        List<Technicist> technicists = technicistMapper.selectOneTcehnicist(id);
        //判断查询的结果是否为空
        if (null != technicists && !"".equals(technicists)){
            //不为空说明查询成功返回结果
            return technicists;
        }
        return null;
    }

    /**
     * @Description: 添加技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Integer
     * @Author: ygy
     * @Date: 2020/6/1 20:18
     */
    public Integer insertTechnicist(Technicist technicist){
        //获取时间
        Date date = new Date();
        //设置时间格式作为创建时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
         //获取系统当前时间的毫秒数
        Long timeMillis = System.currentTimeMillis();
        // 创建Random对象
        Random random = new Random();
        // 做一个随机数，随机区间是0-999之间随机
        Integer randomNum = random.nextInt(9999);
        //把系统当前时间和randomNum相加作为id
        Long id = randomNum + timeMillis;

        Technicist technicist1 = technicist.setId(id).setCreateTime(format);


        int insert = technicistMapper.insert(technicist1);
        //判断新增技术人员受影响的行数
        if (insert>0){
            //返回受影响的行数
            return insert;
        }
        return 0;
    }

    /**
     * @Description:查询要修改的技术人员信息
     * @Param: [id]
     * @return: java.util.List<com.aaa.xj.model.Technicist>
     * @Author: ygy
     * @Date: 2020/6/1 20:38
     */
    public List<Technicist> selectUpdateTechnicist(Long id){
        List<Technicist> technicists = technicistMapper.selectOneTcehnicist(id);
        //判断查询的结果是否为空
        if (null != technicists && !"".equals(technicists)){
            //不为空返回查询结果
            return technicists;
        }
        return null;
    }



    /**
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Boolean
     * @Author: ygy
     * @Date: 2020/5/22 16:16
     */
    public Integer updataTechnicist(Technicist technicist){

        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //把修改时间穿进去
        Technicist technicist1 = technicist.setModifyTime(format);
        //判断技术人员信息是否为空
        if (null != technicist1){
            //不为空就去修改
            int i = technicistMapper.updataTechnicist(technicist1);
            //判断是否修改成功
            if (i>0){
                return i;
            }else {
                return 0;
            }
        }
        return 0;
    }


}
