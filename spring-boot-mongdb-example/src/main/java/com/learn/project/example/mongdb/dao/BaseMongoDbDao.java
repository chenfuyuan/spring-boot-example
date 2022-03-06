package com.learn.project.example.mongdb.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    List<T> selectByIds(Collection<String> commentIds,Class<? extends T> classes);

    void insertAll(Collection<T> modelCollection,Class<? extends T>classes);

    void saveAll(Collection<T> modelCollection,Class<? extends T>classes);
}
