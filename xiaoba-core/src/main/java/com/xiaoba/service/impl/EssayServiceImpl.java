package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.CommentService;
import com.xiaoba.service.EssayService;
import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class EssayServiceImpl implements EssayService {

    private static Integer PAGE_SIZE = 5;

    @Autowired
    EssayMapper essayMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    FileService fileService;

    @Autowired
    CommentService commentService;

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
        if(id==null) {
            return null;
        }
        Essay essay=essayMapper.findEssayById(id);
        if (essay!=null){
            essay.setSavePath(PathContants.ESSAY_PATH+essay.getSavePath());
            List<Tag> tags=tagMapper.getTagsByEssayId(id);
            essay.setTagList(tags);
        }

        return essay;
    }


    @Override
    public String publishEssay(String content,String essayTitle,String essayAbstract, String essayAuthor,String[] tags) {
        String file= UUID.randomUUID().toString();

        String path = fileService.writeToMd(content,file);
//        String fileName = file+".md";
        Essay essay=new Essay();
        essay.setEssayTitle(essayTitle);
        essay.setEssayAbstract(essayAbstract);
        essay.setEssayAuthor(essayAuthor);
        essay.setSavePath(path);
        java.util.Date utilDate=new java.util.Date();
        essay.setEssayPublishTime(new Date(utilDate.getTime()));
        essay.setCategory("未分类");
        int result=essayMapper.insertEssay(essay);
        essay = essayMapper.getEssay(essayTitle, essayAuthor, new Date(utilDate.getTime()));
        for (String tag:tags) {
            addEssayTag(essay.getEssayId(),tag);
        }
        return PathContants.ESSAY_PATH+ path;
    }

    @Override
    public boolean deleteEssay(Integer essayId) {
        Essay essay = essayMapper.findEssayById(essayId);
        int result=essayMapper.deleteEssayById(essayId);
        //删除所有评论
        commentService.deleteCommentsOfEssay(essayId);
        //删除本地文件
        fileService.deletFile(essay.getSavePath());

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
        List<Essay> essays = essayMapper.allOfEssay(pageIndex,PAGE_SIZE);
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

    @Override
    public boolean updateEssay(Essay essay, String content) {
        if (content!=null){
            String file= UUID.randomUUID().toString();
            String path = fileService.writeToMd(content,file);
            fileService.deletFile(essay.getSavePath());
            essay.setSavePath(path);
        }
        int res = essayMapper.updateEssay(essay);
        return res==1;
    }


}
