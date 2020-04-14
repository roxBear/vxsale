package com.rox.vxsale.controller;

import com.rox.vxsale.dto.CommentDTO;
import com.rox.vxsale.entity.Comment;
import com.rox.vxsale.mapper.CommentMapper;
import com.rox.vxsale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    //评论
    @PostMapping("/comment")
    public ResultVo<Comment> comment(@RequestParam("openid") String openid ,
                                @RequestParam("username") String username ,
                                @RequestParam("avatarUrl") String avatarUrl ,
                                @RequestParam("content") String content
                               ){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentName(username);
        commentDTO.setOpenId(openid);
        commentDTO.setCommentContent(content);
        commentDTO.setAvatarUrl(avatarUrl);
        commentDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        commentMapper.create(commentDTO);

        System.out.println(commentDTO.getCommentId());

        return ResultVo.successOf(commentDTO);
    }

    //评论详情
    @GetMapping("/comment/index")
    public ResultVo<Comment> index(@RequestParam("openid") String openid){
        List<Comment> comments =commentMapper.findByOpenId(openid);
        return ResultVo.successOf(comments);
    }



    //所有评论
    @GetMapping("/commentList")
    public ResultVo<List<Comment>> commentList(){
        List<Comment> comments = commentMapper.findAll();
        return ResultVo.successOf(comments);
    }

    //单个用户的所有评论
    @GetMapping("/userCommentList")
    public ResultVo<List<Comment>> userCommentList(@RequestParam("openid") String openid){
        List<Comment> comments =commentMapper.findByOpenId(openid);
        return ResultVo.successOf(comments);
    }
}
