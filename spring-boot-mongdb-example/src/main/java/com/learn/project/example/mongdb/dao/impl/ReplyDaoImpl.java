package com.learn.project.example.mongdb.dao.impl;

import com.learn.project.example.mongdb.dao.ReplyDao;
import com.learn.project.example.mongdb.model.Reply;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:27
 */
@Repository
public class ReplyDaoImpl extends BaseMongoDbDaoimpl<Reply> implements ReplyDao {

    @Override
    public List<Reply> selectByCommentId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("commentId").is(id));
        return mongoTemplate.find(query, Reply.class,Reply.class.getSimpleName());
    }
}
