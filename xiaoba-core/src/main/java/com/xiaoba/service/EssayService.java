package com.xiaoba.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;

import java.util.List;

public interface EssayService {

    /**
     * 得到作者
     * @param author
     * @return
     */
    List<Essay> getEssaies(String author,Integer pageIndex);


    int countOfAuthorEssay(String author);
    /**
     * 得到文章
     * @param id 文章id
     * @return
     */
    Essay getEssay(Integer id);

    /**
     * 发表文章，需要输入文章标题，摘要，作者，以及md文件存储路径
     * @param content 文章内容
     * @param essayTitle  文章标题
     * @param essayAbstract 摘要
     * @param essayAuthor 作者
     * @return 发表成功则返回true
     */
     String publishEssay(String content,String essayTitle,String essayAbstract,String essayAuthor,String[] tags);

    /**
     * 根据文章ID删除文章
     * @param essayId
     * @return
     */
     boolean deleteEssay(Integer essayId);

    /**
     * 得到所有的tag
     * @return
     */
    List<Tag> getAllTags(Integer pageIdenx);

    int countOfAllTags();

    boolean addTag(String tagName);

    List<Essay> allOfEssay(Integer pageIndex);

    int countOfAllofEssay();

    boolean addEssayTag(int essayId,String tagName);
}
