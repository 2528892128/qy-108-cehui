package com.aaa.xj.mapper;

import com.aaa.xj.model.Principal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PrincipalMapper extends Mapper<Principal> {

    List<Principal> qureyOne(Long id);

    Integer deletePrincipal(Long userId);

    Integer updatePrincipal(Principal principal);

    List<Principal> selectOnePrincipal(Long id);
}