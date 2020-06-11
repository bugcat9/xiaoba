package com.xiaoba.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;

import java.util.List;

/**
 * @author zhouning
 */
public interface EssayService {

    /**
     * 返回作者所有文章
     * @param author
     * @param pageIndex
     * @return
     */
    List<Essay> getEssaies(String author,Integer pageIndex);

    /**
     * 返回数量
     * @param author
     * @return
     */
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
     * @param tags 标签
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
     * @param pageIdenx 页数
     * @return
     */
    List<Tag> getAllTags(Integer pageIdenx);

    /**
     * 得到所有标签数量
     * @return
     */
    int countOfAllTags();

    /**
     * 加标签
     * @param tagName
     * @return
     */
    boolean addTag(String tagName);

    /**
     * 得到所有文章
     * @param pageIndex
     * @return
     */
    List<Essay> allOfEssay(Integer pageIndex);

    /**
     * 得到所有文章数量
     * @return
     */
    int countOfAllofEssay();

    /**
     * 添加文章标签
     * @param essayId
     * @param tagName
     * @return
     */
    boolean addEssayTag(int essayId,String tagName);

    /**
     * 更新文章
     * @param essay
     * @param content
     * @return
     */
    boolean updateEssay(Essay essay,String content);
}
