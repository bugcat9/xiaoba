package com.xiaoba.mapper;

import com.xiaoba.entity.Essay;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface EssayMapper {

    /**
     * 插入文章
     * @param essay
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "essayId")
    @Insert("insert into essay(essay_title,essay_abstract,essay_author,essay_publish_time,save_path,category) values(#{essayTitle},#{essayAbstract},#{essayAuthor},#{essayPublishTime},#{savePath},#{category})")
    int insertEssay(Essay essay);

    /**
     * 通过id删除文章
     * @param essayId
     * @return
     */
    @Delete("delete from essay where essay_id=#{essayId}")
    int deleteEssayById(Integer essayId);

    /**
     * 更新文章
     * @param essay
     * @return
     */
    @Update("update essay set essay_title=#{essayTitle},essay_author=#{essayAuthor},essay_publish_time=#{essayPublishTime},save_path=#{savePath}")
    int updateEssay(Essay essay);

    /**
     * 根据essayTitle、essayAuthor、essayPublishTime得到文章
     * @param essayTitle
     * @param essayAuthor
     * @param essayPublishTime
     * @return
     */
    @Select("select * from essay where essay_title=#{essayTitle} and essay_author=#{essayAuthor} and essay_publish_time=#{essayPublishTime}")
    Essay getEssay(String essayTitle, String essayAuthor, Date essayPublishTime);

    /**
     * 根据文章id得到文章
     * @param essayId
     * @return
     */
    @Select("select * from essay where essay_id=#{essayId}")
    Essay findEssayById(Integer essayId);

    /**
     * 得到一个作者的所有文章，进行分页
     * @param essayAuthor
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from essay where essay_author=#{essayAuthor} order by essay_publish_time limit ${pageIndex*count},#{count}")
    List<Essay> listEssay(String essayAuthor,int pageIndex,int count);

    /**
     * 得到一个作者所有文章数量
     * @param essayAuthor
     * @return
     */
    @Select("select count(*) from essay where essay_author=#{essayAuthor}")
    int countOfAuthorEssay(String essayAuthor);

    /**
     * 当文章被评论时，评论数加一
     * @param essayId 文章id
     * @return 修改成功返回1
     */
    @Update("update essay set comment_num=comment_num+1 where essay_id=#{essayId}")
    int addCommentNum(Integer essayId);

    /**
     * 文章评论被删时，评论数减一
     * @param essayId 文章id
     * @return 修改成功返回1
     */
    @Update("update essay set comment_num=comment_num-1 where essay_id=#{essayId} and comment_num>0")
    int decCommentNum(Integer essayId);

    /**
     * 得到所有文章
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from essay order by essay_publish_time limit ${pageIndex*count},#{count}")
    List<Essay> allOfEssay(int pageIndex,int count);

    /**
     * 得到所有文章数量
     * @return
     */
    @Select("select count(*) from essay")
    int countOfAllofEssay();

    /**
     * 更新文章作者名字
     * @param oldName   旧名字
     * @param lastName  新名字
     * @return
     */
    @Update("UPDATE essay SET essay_author=#{lastName} WHERE essay_author=#{oldName}")
    int updateEssayName(String oldName,String lastName);

}
