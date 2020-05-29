package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.UserMapper;
import com.aaa.xj.model.User;
import com.aaa.xj.redis.RedisService;
import com.aaa.xj.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.xj.staticstatus.TimeProperties.TIME_TYPE;
import static com.aaa.xj.staticstatus.UserPassword.USER_PASSWORD;

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
    public PageInfo selectAllUser(User user,Integer pageNo,Integer pageSize){
        try {
            //判断前段是否传值成功
            if (!"".equals(pageNo) && !"".equals(pageSize)){
                //调用分页方法
                PageInfo<User> userPageInfo = queryListByPage(user, pageNo, pageSize);
                //判断查询是否成功
                if (!"".equals(userPageInfo) && null !=userPageInfo){
                    //成功返回
                    return userPageInfo;
                }else {
                    //不成功再次查询
                    PageInfo<User> userPageInfo1 = queryListByPage(user, pageNo, pageSize);
                    return userPageInfo1;
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
     *      新增用户
     * @Data: 2020/5/26
     * @param user
     * @Return:java.lang.Boolean
     */
    public Boolean addUser(User user){

        try {

            //获取当前时间
            Date date = new Date();
            //设置日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            //获取一个token值
            String token= IDUtils.getUUID();
            //获取前段传递的参数 放入user中
            //将username传入

            user.setUsername(user.getUsername())
                    .setToken(token)
                    //将password传入
                    .setPassword(user.getPassword())
                    //将Email传入
                    .setEmail(user.getEmail())
                    //将手机号传入
                    .setMobile(user.getMobile())
                    //将角色传入
                    .setType(user.getType())
                    //将部门传入
                    .setDeptId(user.getDeptId())
                    //将状态传入
                    .setStatus(user.getStatus())
                    //将性别传入
                    .setSsex(user.getSsex())
                    //将创建时间传入
                    .setCreateTime(format)
                    //传入id
                    .setId(user.getId());




            int insert = userMapper.insert(user);
            if (insert>0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      通过主键删除用户
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    public Integer deleteUser(User user){
        //判断前段是否传值成功
        if (!"".equals(user) && null !=user){
            try {
                //执行删除操作
                Integer delete = delete(user);
                if (delete>0){
                    return delete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id批量删除用户
     * @Data: 2020/5/26
     * @param [ids]
     * @Return:java.lang.Integer
     */
    public Integer deleteMoreUser(List<Object> ids){
        //判断前段是否传值成功
        if (!"".equals(ids) && null !=ids){
            try {
                //调用父类的批量删除方法
                Integer integer = super.batchDelete(ids);
                //判断是否查询出结果
                if (integer>0){
                    return integer;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     * @Data: 2020/5/28
     * @param [id]
     * @Return:com.aaa.xj.model.User
     */
    public User selectUserById(Long id){
        //判断前端是否传值成功
        if (!"".equals(id) && null !=id){
            try {
                //通过id查询信息
                User user = userMapper.selectByPrimaryKey(id);
                //判断是否查询出数据
                if (!"".equals(user) && null !=user){
                    return user;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id修改用户信息
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    public Integer updateUser(User user){
        if (!"".equals(user) && null !=user){
            //获取当前时间作为修改时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
            String format = simpleDateFormat.format(new Date());
            //把时间存到实体中
            user.setModifyTime(format);
            try {
                //通过父类方法修改用户信息
                Integer update = super.update(user);
                //判断受影响的行数
                if (update>0){
                    return update;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *          根据用户性别查询
     * @Data: 2020/5/26
     * @param [ssex, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    public PageInfo selectUserBySsex(String ssex,Integer pageNo,Integer pageSize){

        //判断前端传值是否成功
        if (!"".equals(ssex) && !"".equals(pageNo) && !"".equals(pageSize)){
            // 当前页数和一页数量
            PageHelper.startPage(pageNo,pageSize);
            //定义users
            List<User> users = null;
            try {
                //查询用户信息
                users = userMapper.selectUserBySsex(ssex);
                //判断查询结果是否为空
                if (!"".equals(users) && null !=users){
                    //不是空 将结果放入
                    PageInfo<User> userPageInfo = new PageInfo<>(users);
                    //返回userPageInfo
                    return userPageInfo;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据状态查询用户信息
     * @Data: 2020/5/26
     * @param [status, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    public PageInfo selectUserBySta(String status,Integer pageNo,Integer pageSize){
        //判断前端传值是否成功

        if (!"".equals(status) && !"".equals(pageNo) && !"".equals(pageSize)){
            // 当前页数和一页数量
            PageHelper.startPage(pageNo,pageSize);
            //定义users
            List<User> users = null;
            try {
                //查询用户信息
                users = userMapper.selectUserBySta(status);
                //判断查询结果是否为空
                if (!"".equals(users) && null !=users){
                    //不是空 将结果放入
                    PageInfo<User> userPageInfo = new PageInfo<>(users);
                    //返回userPageInfo
                    return userPageInfo;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      密码重置成初始密码
     * @Data: 2020/5/29
     * @param [user]
     * @Return:java.lang.Integer
     */
    public Integer ResetUserPwd(User user){

        //将初始密码set到实体
        user.setPassword(USER_PASSWORD);
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TYPE);
        String format = simpleDateFormat.format(new Date());
        //把时间存到实体中
        user.setModifyTime(format);
        //判断前端是否传值成功
        if (!"".equals(user)  && null !=user){

            try {
                //调用父类的方法
                Integer update = super.update(user);
                if (update>0){
                    return update;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
