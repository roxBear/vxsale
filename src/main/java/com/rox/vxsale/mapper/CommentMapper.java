package com.rox.vxsale.mapper;

import com.rox.vxsale.dto.CommentDTO;
import com.rox.vxsale.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Mapper
public interface CommentMapper {

    @Insert("insert into `comment` (comment_name,openid,comment_content,create_time)" +
            "values(#{commentName},#{openId},#{commentContent},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "commentId",keyColumn = "comment_id")
    int create(CommentDTO commentDTO);

    @Select("select * from `comment` where openid = #{openId}")
    List<Comment> findByOpenId(String openId);

    @Select("select * from `comment`")
    List<Comment> findAll();

    @Delete("delete from `comment` where comment_id = #{commentId}")
    int delete(Integer commentId);
}
