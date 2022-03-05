package com.learn.project.example.mongdb.dto;

import com.learn.project.example.mongdb.model.Reply;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:20
 */
@Data
public class CommentDTO {

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
}
