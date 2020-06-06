package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.CategoryMapper;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    EssayMapper essayMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Essay> getEssaies(String author) {
        List<Essay> essays = essayMapper.listEssay(author);
        //设置访问位置
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
