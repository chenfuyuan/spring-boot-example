package com.learn.project.example.mongdb.dao.impl;

import com.learn.project.example.mongdb.dao.CommentDao;
import com.learn.project.example.mongdb.model.Comment;
import com.learn.project.example.mongdb.model.Reply;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:27
 */
@Repository
public class CommentDaoImpl extends BaseMongoDbDaoimpl<Comment> implements CommentDao {
    @Override
    public void save(Comment model) {
        model.setReplyList(model.getReplyList().stream().map(item->new Reply(item.getId())).collect(Collectors.toList()));
        super.save(model);
    }
}
