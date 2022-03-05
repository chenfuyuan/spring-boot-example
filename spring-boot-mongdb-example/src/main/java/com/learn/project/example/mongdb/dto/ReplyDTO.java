package com.learn.project.example.mongdb.dto;


import lombok.Data;

/**
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:20
 */
@Data
public class ReplyDTO {

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
     * 类型
     */
    private String type;
}
