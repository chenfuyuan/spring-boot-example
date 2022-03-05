package com.learn.project.example.mongdb.dao.impl;

import com.learn.project.example.mongdb.dao.BaseMongoDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:27
 */
@Repository
public class BaseMongoDbDaoimpl<T> implements BaseMongoDbDao<T> {
    @Autowired
    protected MongoTemplate mongoTemplate;


    @Override
    public void insert(T model) {
        mongoTemplate.insert(model,model.getClass().getSimpleName());
    }


    @Override
    public T selectById(String commentId,Class<? extends T> classes,String collectionName) {
        return (T)mongoTemplate.findById(commentId, classes, collectionName);
    }

    @Override
    public void save(T model) {
        mongoTemplate.save(model, model.getClass().getSimpleName());
    }


}
