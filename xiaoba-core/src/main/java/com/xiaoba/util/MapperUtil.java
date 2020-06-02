package com.xiaoba.util;

import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MapperUtil {

    @Autowired
    static EssayMapper essayMapper;

    public static List<Essay> getEssays(){
        return essayMapper.getAllEssay();
    }
    
    public void test(){}
}
