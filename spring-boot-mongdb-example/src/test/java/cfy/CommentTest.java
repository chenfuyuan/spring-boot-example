package cfy;

import com.learn.project.example.mongdb.ExampleMongoDbApplication;
import com.learn.project.example.mongdb.dto.CommentDTO;
import com.learn.project.example.mongdb.dto.ReplyDTO;
import com.learn.project.example.mongdb.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        replyDTO.setCommentId("3630f8ba-b955-4cb6-b1ae-d7a35b11f2ed");
        replyDTO.setAccountId("cfy");
        replyDTO.setTargetId("3630f8ba-b955-4cb6-b1ae-d7a35b11f2ed");
        replyDTO.setType("2");
        commentService.save(replyDTO);

    }
}
