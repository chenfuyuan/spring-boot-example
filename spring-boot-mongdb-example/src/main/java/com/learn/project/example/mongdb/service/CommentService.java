package com.learn.project.example.mongdb.service;

import com.learn.project.example.mongdb.dto.CommentDTO;
import com.learn.project.example.mongdb.dto.ReplyDTO;
import com.learn.project.example.mongdb.model.Comment;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:19
 */
public interface CommentService {

    void save(CommentDTO DTO);

    void save(ReplyDTO replyDTO);

    void saveAll(List<ReplyDTO> replyDTO);
}
