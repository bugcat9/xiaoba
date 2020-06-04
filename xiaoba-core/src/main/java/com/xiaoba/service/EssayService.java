package com.xiaoba.service;

import com.xiaoba.entity.Essay;

import java.util.List;

public interface EssayService {
    List<Essay> getEssaies(String author);

    Essay getEssay( Integer id);

    /**
     * 发表文章，需要输入文章标题，摘要，作者，以及md文件存储路径
     * @param essayTitle  文章标题
     * @param essayAbstract 摘要
     * @param essayAuthor 作者
     * @param savePath md文件存储路径
     * @return 发表成功则返回true
     */
     boolean publishEssay(String essayTitle,String essayAbstract,String essayAuthor,String savePath);

    /**
     * 根据文章ID删除文章
     * @param essayId
     * @return
     */
     boolean deleteEssay(Integer essayId);
}
