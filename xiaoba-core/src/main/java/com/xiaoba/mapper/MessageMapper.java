package com.xiaoba.mapper;

import com.xiaoba.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface MessageMapper {

    /**
     * 插入
     * @param message
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "messageId")
    @Insert("insert into message (message_sender,message_receiver,message_status,content,send_time) " +
            "values(#{messageSender},#{messageReceiver},#{messageStatus},#{content},#{sendTime})")
    int insertMessage(Message message);

    /**
     * 删除文章
     * @param messageId
     * @return
     */
    @Delete("delete from message where message_id=#{messageId}")
    int deletMessage(int messageId);

    /**
     * 更新信息状态，由已读变成未读
     * @param messageId
     * @param messageStatus
     * @return
     */
    @Update("update message set message_status=#{messageStatus} where message_id=#{messageId}")
    int updateStatus(Integer messageId,Integer messageStatus);

    /**
     * 得到接收者 已读或者未读的消息
     * @param receiver
     * @param messageStatus
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from message  where message_receiver=#{receiver} and message_status=#{messageStatus} " +
            "order by send_time limit ${pageIndex*count},#{count} ")
    List<Message> selectMsgByRec(String receiver,int messageStatus,int pageIndex,int count);

    /**
     * 信息数量
     * @param receiver
     * @param messageStatus
     * @return
     */
    @Select("select count(*) from message   where message_receiver=#{receiver} and message_status=#{messageStatus}")
    int countOfMsg(String receiver,int messageStatus);

}
