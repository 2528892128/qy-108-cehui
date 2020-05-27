package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.TechnicistMapper;
import com.aaa.xj.model.Technicist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * @Description: 修改技术人员信息
     * @Param: [technicist]
     * @return: java.lang.Boolean
     * @Author: ygy
     * @Date: 2020/5/22 16:16
     */
    public Boolean updataTechnicist(Technicist technicist){

        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //获取技术人员信息
        Technicist technicist1 = technicist.setId(technicist.getId())
                .setName(technicist.getName())
                .setIdNumber(technicist.getIdNumber())
                .setMajorType(technicist.getMajorType())
                .setSex(technicist.getSex())
                .setAge(technicist.getAge())
                .setMajor(technicist.getMajor())
                .setDuty(technicist.getDuty())
                .setTitleMajor(technicist.getTitleMajor())
                .setModifyTime(format);
        //判断技术人员信息是否为空
        if (null != technicist1){
            //不为空就去修改
            int i = technicistMapper.updataTechnicist(technicist1);
            //判断是否修改成功
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


}
