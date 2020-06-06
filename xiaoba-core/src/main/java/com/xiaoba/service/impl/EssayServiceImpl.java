package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.EssayService;
import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    EssayMapper essayMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    FileService fileService;

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

        Essay essay=essayMapper.findEssayById(id);
        essay.setSavePath(PathContants.ESSAY_PATH+essay.getSavePath());
        return essay;
    }


    @Override
    public String publishEssay(String content,String essayTitle,String essayAbstract, String essayAuthor) {

        String path = fileService.writeToMd(content,essayTitle);
        String fileName = essayTitle+".md";
        Essay essay=new Essay();
        essay.setEssayTitle(essayTitle);
        essay.setEssayAbstract(essayAbstract);
        essay.setEssayAuthor(essayAuthor);
        essay.setSavePath(fileName);
        essay.setEssayPublishTime(new Date());
        int result=essayMapper.insertEssay(essay);

        return path;
    }

    @Override
    public boolean deleteEssay(Integer essayId) {
        int result=essayMapper.deleteEssayById(essayId);
        return result==1;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.listTags();
    }

    @Override
    public boolean addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        int res = tagMapper.addTag(tag);
        return res==1;
    }
}
