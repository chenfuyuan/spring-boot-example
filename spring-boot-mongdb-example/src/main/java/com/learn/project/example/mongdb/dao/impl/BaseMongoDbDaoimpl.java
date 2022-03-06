package com.learn.project.example.mongdb.dao.impl;

import com.learn.project.example.mongdb.dao.BaseMongoDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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

    @Override
    public List<T> selectByIds(Collection<String> commentIds,Class<? extends T> classes) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(commentIds));
        return (List<T>) mongoTemplate.find(query, classes, classes.getSimpleName());
    }

    @Override
    public void insertAll(Collection<T> modelCollection,Class<? extends T>classes) {
        mongoTemplate.insert(modelCollection, classes.getSimpleName());
    }

    @Override
    public void saveAll(Collection<T> modelCollection,Class<? extends T>classes) {
        modelCollection.forEach(this::save);
    }


}
