package com.learn.project.example.mongdb.dao;


import com.learn.project.example.mongdb.model.Reply;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:21
 */
public interface ReplyDao extends BaseMongoDbDao<Reply> {

    List<Reply> selectByCommentId(String id);
}
