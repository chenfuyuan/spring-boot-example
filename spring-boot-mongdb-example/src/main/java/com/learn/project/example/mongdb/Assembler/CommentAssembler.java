package com.learn.project.example.mongdb.Assembler;
import com.learn.project.example.mongdb.model.Reply;
import java.util.ArrayList;
import java.util.UUID;

import com.learn.project.example.mongdb.dto.CommentDTO;
import com.learn.project.example.mongdb.model.Comment;


/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:32
 */
public class CommentAssembler {
    private CommentAssembler() {

    }

    public static Comment toComment(CommentDTO commentDTO) {
        Comment result = new Comment();
        result.setId(UUID.randomUUID().toString());
        result.setContent(commentDTO.getContent());
        result.setAccountId(commentDTO.getAccountId());
        result.setMomentId(commentDTO.getMomentId());
        return result;
    }

}
