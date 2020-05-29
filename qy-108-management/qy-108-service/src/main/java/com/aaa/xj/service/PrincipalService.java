package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.PrincipalMapper;
import com.aaa.xj.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @program: qy-108-cehui
 * @description:
 * @author: ygy
 * @create: 2020-05-20-2020/5/20 14:38
 */
@Service
public class PrincipalService extends BaseService<Principal> {

    @Autowired
    private PrincipalMapper principalMapper;

    /**
     * @Description: 获取负责人的信息
     * @Param: [userId]
     * @return: java.lang.String
     * @Author: ygy
     * @Date: 2020/5/20 14:50
     */
    public List<Principal> qureyOne(Long userId){
        //获取信息
        List<Principal> principal = principalMapper.qureyOne(userId);
        //判断负责人的信息是否为空
        if (null != principal){
            //不为空就返回信息
            return principal;
        }
        return null;
    }

    /**
     * @Description: 修改负责人信息
     * @Param: [principal]
     * @return: int
     * @Author: ygy
     * @Date: 2020/5/21 19:21
     */
    public Boolean updateList(Principal principal){
        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //获取负责人信息
        Principal principal1 = principal.setId(principal.getId())
                .setType(principal.getType())
                .setName(principal.getName())
                .setIdNumber(principal.getIdNumber())
                .setAge(principal.getAge())
                .setSex(principal.getSex())
                .setMajor(principal.getMajor())
                .setDuty(principal.getDuty())
                .setModifyTime(format);
        //判断负责人信息是否为空
        if (null != principal1){
            //不为空就修改
            int i = principalMapper.updateList(principal1);
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
