package com.learn.project.example.mongdb.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:14
 */
@Data
public class Comment {


    /**
     * id
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 账户Id
     */
    private String accountId;

    /**
     * 动态Id
     */
    private String momentId;

    /**
     * 回复List
     */
    @DBRef
    private List<Reply> replyList;

    public void addReply(Reply reply) {
        if (replyList == null) {
            this.replyList = new ArrayList<>();
        }

        replyList.add(reply);
    }
}
