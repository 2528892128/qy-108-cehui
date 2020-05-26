package com.aaa.xj.mapper;

import com.aaa.xj.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecialPostMapper extends Mapper<SpecialPost> {

    List<SpecialPost> selectSpecialPost(Long userId);

}