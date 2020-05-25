package com.aaa.xj.mapper;

import com.aaa.xj.model.Mapping_unit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface Mapping_unitMapper extends Mapper<Mapping_unit> {

    List<Mapping_unit> qureyMapping_unit(Long userId);

}