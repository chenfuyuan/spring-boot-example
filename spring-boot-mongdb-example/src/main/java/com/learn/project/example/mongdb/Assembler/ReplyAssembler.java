package com.learn.project.example.mongdb.Assembler;

import com.learn.project.example.mongdb.dto.ReplyDTO;
import com.learn.project.example.mongdb.model.Reply;

import java.util.UUID;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:36
 */
public class ReplyAssembler {

    public static Reply toReply(ReplyDTO source) {
        Reply result = new Reply("");
        result.setId(UUID.randomUUID().toString());
        result.setContent(source.getContent());
        result.setCommentId(source.getCommentId());
        result.setAccountId(source.getAccountId());
        result.setTargetId(source.getTargetId());
        result.setType(source.getType());
        return result;
    }
}
