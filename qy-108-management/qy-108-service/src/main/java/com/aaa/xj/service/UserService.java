package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.UserMapper;
import com.aaa.xj.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询所有用户
     * @Data: 2020/5/23 18:21
     * @param null
     * @Return:
     */
    public List<User> selectAllUser(){

        List<User> users = null;
        try {
            //查询所有user信息
            users = userMapper.selectAll();
            //判断是否为空
            if (!"".equals(users) && null !=users){
                //不为空 返回users
                return users;
            }else {
                //如果为空再次查询
                List<User> list = userMapper.selectAll();
                //再次判断
                if (!"".equals(list) && null !=list){
                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo<User> queryListByPage(User user, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        try {
            List<User> users = userMapper.selectAll();
            if (!"".equals(users) && null !=users){

                //不为空 返回users
                PageInfo<User> pageInfo = new PageInfo<User>(users);
                return pageInfo;
            }else {
                //如果为空再次查询
                List<User> list = userMapper.selectAll();
                //再次判断
                if (!"".equals(list) && null !=list){
                    PageInfo<User> pageInfo1 = new PageInfo<User>(list);
                    return pageInfo1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
