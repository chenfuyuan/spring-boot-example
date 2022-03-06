package com.learn.project.example.mongdb.model;

import lombok.Data;


import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:14
 */
@Data
public class Reply {

    public Reply(String id) {
        this.id = id;
    }

    public Reply() {
    }

    /**
     * id
     */
    private String id;
    /**
     * 评论
     */
    private String content;
    /**
     * 评论id
     */
    private String commentId;

    /**
     * 账户Id
     */
    private String accountId;

    /**
     * 回复对象-评论或回复
     */
    private String targetId;
    /**
     * 回复对象Id
     */
    private String targetAccountId;

    /**
     * 类型
     */
    private String type;

    /**
     * 回复列表
     */
    private List<Reply> replyList;
}
