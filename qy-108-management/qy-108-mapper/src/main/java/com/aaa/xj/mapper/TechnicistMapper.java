package com.aaa.xj.mapper;

import com.aaa.xj.model.Technicist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TechnicistMapper extends Mapper<Technicist> {

    List<Technicist> qureyTechnicist(Long id);

    int updataTechnicist(Technicist technicist);

}