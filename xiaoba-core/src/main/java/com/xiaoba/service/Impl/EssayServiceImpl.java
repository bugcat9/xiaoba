package com.xiaoba.service.Impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.service.EssayService;
import com.xiaoba.util.PathLoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<Essay> getEssaies(String author) {
        List<Essay> essays = essayMapper.listEssay(author);
        for (Essay e : essays) {
            e.setSavePath(PathContants.ESSAY_PATH+e.getSavePath());
        }
        return essays;
    }

    @Override
    public Essay getEssay(Integer id) {
        return essayMapper.findEssayById(id);
    }
}
