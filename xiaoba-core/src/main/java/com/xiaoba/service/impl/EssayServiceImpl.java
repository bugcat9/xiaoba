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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    private static Integer PAGE_SIZE = 5;

    @Autowired
    EssayMapper essayMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    FileService fileService;

    @Override
    public List<Essay> getEssaies(String author,Integer pageIdex) {
        List<Essay> essays = essayMapper.listEssay(author,pageIdex,PAGE_SIZE);
        //设置访问位置
        for (Essay e : essays) {
            e.setSavePath(PathContants.ESSAY_PATH+e.getSavePath());
            //得到标签，标签没有id，不过没有影响
            List<Tag> tags=tagMapper.getTagsByEssayId(e.getEssayId());
//            for(Tag tag:tags){
//            }
            e.setTagList(tags);
        }
        return essays;
    }

    @Override
    public int countOfAuthorEssay(String author) {
        return essayMapper.countOfAuthorEssay(author);
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
        java.util.Date utilDate=new java.util.Date();
        essay.setEssayPublishTime(new Date(utilDate.getTime()));
        int result=essayMapper.insertEssay(essay);

        return path;
    }

    @Override
    public boolean deleteEssay(Integer essayId) {
        int result=essayMapper.deleteEssayById(essayId);
        return result==1;
    }

    @Override
    public List<Tag> getAllTags(Integer pageIndex) {
        return tagMapper.listTags(pageIndex,PAGE_SIZE);
    }

    @Override
    public int countOfAllTags() {
        return tagMapper.countOfTags();
    }

    @Override
    public boolean addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        int res = tagMapper.addTag(tag);
        return res==1;
    }

    @Override
    public List<Essay> allOfEssay(Integer pageIndex) {
        return essayMapper.allOfEssay(pageIndex);
    }

    @Override
    public int countOfAllofEssay() {
        return essayMapper.countOfAllofEssay();
    }

    @Override
    public boolean addEssayTag(int essayId, String tagName) {

        Tag tag = tagMapper.getTagsByName(tagName);
        if(tag==null){
            tag = new Tag();
            tag.setTagName(tagName);
            tagMapper.addTag(tag);
        }
        int res = tagMapper.addEssayTag(essayId, tagName);
        return res==1;
    }
}
