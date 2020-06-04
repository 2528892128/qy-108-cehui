package com.aaa.xj.mapper;

import com.aaa.xj.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecialPostMapper extends Mapper<SpecialPost> {

    /**
     * 根据userid查询所有的特殊岗位人员
     * @param userId
     * @return
     */
    List<SpecialPost> selectSpecialPost(Long userId);

    /**
     * liukai
     * 根据id查询单条特殊岗位人员数据
     * @param id
     * @return
     */
    List<SpecialPost> selectOneSpecialPost(Long id);


    /**
     * liukai
     * 根据实体进行特殊岗位人员信息修改
     * @param specialPost
     * @return
     */
    Integer updateSpecialPost(SpecialPost specialPost);
    List<SpecialPost> selectByKeySpecialPost(Long id);

    /**
     * liukai
     * 根据id进行删除特殊岗位人员信息
     */
    Integer deleteSpecialPost(Long id);
}