package com.xiaoba.mapper;

import com.xiaoba.entity.Tag;

import java.util.List;

public interface TagMapper {

    List<Tag> getTagsByEssayId(Integer essayId);

}
