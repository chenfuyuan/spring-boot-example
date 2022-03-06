package com.learn.project.example.mongdb.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/6 20:00
 */
public class MyLookUpOperation implements AggregationOperation {

    private String from;

    private String localField;

    private String foreignField;

    private String as;

    public MyLookUpOperation(String from, String localField, String foreignField, String as) {
        this.from = from;
        this.localField = localField;
        this.foreignField = foreignField;
        this.as = as;
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        Document lookUpDocument = new Document();
        lookUpDocument.append("from", from);
        lookUpDocument.append("localField",localField );
        lookUpDocument.append("foreignField", foreignField);
        lookUpDocument.append("as", as);
        return new Document("$lookup", lookUpDocument);
    }
}
