package com.aaa.xj.mapper;

import com.aaa.xj.model.User;
import com.aaa.xj.vo.TokenVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    //根据性别查询

    List<User> selectUserBySsex(String ssex);

    //根据状态查询用户

    List<User> selectUserBySta(String status);
}