package com.aaa.xj.mapper;

import com.aaa.xj.model.Principal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PrincipalMapper extends Mapper<Principal> {

    List<Principal> qureyOne(Long id);

    int updateList(Principal principal);

}