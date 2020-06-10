package com.xiaoba.mapper;

import com.xiaoba.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface MessageMapper {

    @Options(useGeneratedKeys = true,keyProperty = "messageId")
    @Insert("insert into message (message_sender,message_receiver,message_status,content,send_time) " +
            "values(#{messageSender},#{messageReceiver},#{messageStatus},#{content},#{sendTime})")
    int insertMessage(Message message);

    @Delete("delete from message where message_id=#{messageId}")
    int deletMessage(int messageId);

//    @Update("update message set message_id=#{messaheId},")
//    int updateMessage(Message message)

    @Update("update message set message_status=#{messageStatus} where message_id=#{messageId}")
    int updateStatus(Integer messageId,Integer messageStatus);

    @Select("select * from message  where message_receiver=#{receiver} and message_status=#{messageStatus} " +
            "order by send_time limit ${pageIndex*count},#{count} ")
    List<Message> selectMsgByRec(String receiver,int messageStatus,int pageIndex,int count);

    @Select("select count(*) from message   where message_receiver=#{receiver} and message_status=#{messageStatus}")
    int countOfMsg(String receiver,int messageStatus);

}
