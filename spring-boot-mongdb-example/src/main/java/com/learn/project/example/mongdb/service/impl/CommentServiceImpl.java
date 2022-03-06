package com.learn.project.example.mongdb.service.impl;


import com.learn.project.example.mongdb.Assembler.CommentAssembler;
import com.learn.project.example.mongdb.Assembler.ReplyAssembler;
import com.learn.project.example.mongdb.dao.CommentDao;
import com.learn.project.example.mongdb.dao.ReplyDao;
import com.learn.project.example.mongdb.dto.CommentDTO;
import com.learn.project.example.mongdb.dto.ReplyDTO;
import com.learn.project.example.mongdb.model.Comment;
import com.learn.project.example.mongdb.model.Reply;
import com.learn.project.example.mongdb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:19
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ReplyDao replyDao;

    @Override
    public void save(CommentDTO commentDTO) {
        commentDao.insert(CommentAssembler.toComment(commentDTO));
    }

    @Override
    public void save(ReplyDTO replyDTO) {
        //校验commentId是否存在
        Comment comment = commentDao.selectById(replyDTO.getCommentId(), Comment.class, Comment.class.getSimpleName());
        System.out.println(comment);
        Reply reply = ReplyAssembler.toReply(replyDTO);
//        List<Reply> replyList = replyDao.selectByCommentId(comment.getId());
//        comment.setReplyList(replyList);
        if (replyDTO.getType().equals("2")) {
            comment.addReply(reply);
            commentDao.save(comment);
            reply.setTargetAccountId(comment.getAccountId());
        }
        replyDao.insert(reply);
    }

    @Override
    public void saveAll(List<ReplyDTO> replyDTO) {
        Set<String> commentIds = replyDTO.stream().map(ReplyDTO::getCommentId).collect(Collectors.toSet());
        List<Comment> commentList = commentDao.selectByIds(commentIds, Comment.class);
        Map<String, Comment> commentMap = Optional.ofNullable(commentList).orElseThrow(() -> new RuntimeException()).stream().collect(Collectors.toMap(Comment::getId, Function.identity()));
        List<Reply> replyList = replyDTO.stream().map(ReplyAssembler::toReply).collect(Collectors.toList());
        replyList.stream().forEach(item->{
            if (item.getType().equals("2")) {
                Optional.ofNullable(commentMap.get(item.getCommentId())).ifPresent(comment->{
                    comment.addReply(item);
                    item.setTargetAccountId(comment.getAccountId());
                });
            }
        });

        commentDao.saveAll(commentMap.values(), Comment.class);
        replyDao.insertAll(replyList,Reply.class);

    }
}
