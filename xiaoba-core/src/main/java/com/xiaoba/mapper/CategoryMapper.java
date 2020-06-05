package com.xiaoba.mapper;

import com.xiaoba.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Category SelectCateByEassyId(Integer EassyId);

}
