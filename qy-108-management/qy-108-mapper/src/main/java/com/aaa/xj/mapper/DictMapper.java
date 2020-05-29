package com.aaa.xj.mapper;

import com.aaa.xj.model.Dict;
import tk.mybatis.mapper.common.Mapper;

public interface DictMapper extends Mapper<Dict> {

    Dict selectUpdateDict(Long dictId);

    Integer updateDict(Dict dict);

}