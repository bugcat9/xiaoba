package com.xiaoba.service.Impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.service.EssayService;
import com.xiaoba.util.PathLoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    @Override
    public boolean publishEssay(String essayTitle, String essayAbstract, String essayAuthor, String savePath) {
        Essay essay=new Essay();
        essay.setEssayTitle(essayTitle);
        essay.setEssayAbstract(essayAbstract);
        essay.setEssayAuthor(essayAuthor);
        essay.setSavePath(savePath);
        essay.setEssayPublishTime(new Date());
        int result=essayMapper.insertEssay(essay);
        return result==1;
    }

    @Override
    public boolean deleteEssay(Integer essayId) {
        int result=essayMapper.deleteEssayById(essayId);
        return result==1;
    }
}
