package cfy;

import com.learn.project.example.mongdb.ExampleMongoDbApplication;
import com.learn.project.example.mongdb.dto.CommentDTO;
import com.learn.project.example.mongdb.dto.ReplyDTO;
import com.learn.project.example.mongdb.model.Comment;
import com.learn.project.example.mongdb.service.CommentService;
import com.learn.project.example.mongdb.util.MyLookUpOperation;
import com.learn.project.example.mongdb.util.RemoveDollarOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/3/5 21:39
 */
@SpringBootTest(classes = ExampleMongoDbApplication.class)
public class CommentTest {

    @Autowired
    private CommentService commentService;


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void comment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("评论内容");
        commentDTO.setMomentId("1");
        commentDTO.setAccountId("cfy");
        commentService.save(commentDTO);
    }

    @Test
    public void reply() {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setContent("这是一条回复");
        replyDTO.setCommentId("6cfd96ec-abc3-45d6-9fad-cbc9e15b8346");
        replyDTO.setAccountId("cfy");
        replyDTO.setTargetId("6cfd96ec-abc3-45d6-9fad-cbc9e15b8346");
        replyDTO.setType("2");
        commentService.save(replyDTO);
    }

    @Test
    public void replyBatch() {
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setContent("这是一条回复"+i);
            replyDTO.setCommentId("6cfd96ec-abc3-45d6-9fad-cbc9e15b8346");
            replyDTO.setAccountId("cfy");
            replyDTO.setTargetId("6cfd96ec-abc3-45d6-9fad-cbc9e15b8346");
            replyDTO.setType("2");
            replyDTOList.add(replyDTO);
        }
        commentService.saveAll(replyDTOList);
    }


    @Test
    public void findComment() {
        LookupOperation lookup = Aggregation.lookup("Reply", "replyList._id", "_id", "replyList");
        Criteria criteria = Criteria.where("_id").is("6cfd96ec-abc3-45d6-9fad-cbc9e15b8346");
        Aggregation aggregation = Aggregation.newAggregation(
                lookup ,
                Aggregation.match(criteria)
        );
        AggregationResults<Comment> aggregate = mongoTemplate.aggregate(aggregation, Comment.class.getSimpleName(), Comment.class);
        System.out.println(aggregate.getMappedResults());
    }

    @Test
    public void testRemoveDollarOperation() {
        //展平多的一方
        UnwindOperation unwindOperation = new UnwindOperation(Fields.field("replyList"));

        //消除@DBRef引用对象中的$id的$符号
        RemoveDollarOperation removeDollarOperation = new RemoveDollarOperation("newReplyFieldName","replyList");

        LookupOperation lookupOperation = LookupOperation.newLookup().from("Reply").localField("newReplyFieldName.id").foreignField("_id").as("replyList");

        MatchOperation matchOperation = new MatchOperation(Criteria.where("_id").is("3630f8ba-b955-4cb6-b1ae-d7a35b11f2ed"));

        Aggregation aggregation = Aggregation.newAggregation(unwindOperation,removeDollarOperation,lookupOperation,matchOperation);

        AggregationResults<Comment> aggregate = mongoTemplate.aggregate(aggregation, Comment.class.getSimpleName(), Comment.class);
        System.out.println(aggregate.getMappedResults());


    }
}
