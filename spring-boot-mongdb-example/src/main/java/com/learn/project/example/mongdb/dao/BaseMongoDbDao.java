package com.learn.project.example.mongdb.dao;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:28
 */
public interface BaseMongoDbDao<T>{
    void insert(T model);

    T selectById(String commentId,Class<? extends T> classes,String collectionName);

    void save(T model);
}
