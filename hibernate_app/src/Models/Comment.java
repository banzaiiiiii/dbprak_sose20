package Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "comment", schema = "public", catalog = "socialnetwork")
public class Comment {

    private long commentId;

    @Id
    @Column(name = "comment_id")
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Id
    @Column(name = "comment_message_id")
    private long commentMessageId;

    public long getCommentMessageId() {
        return commentMessageId;
    }

    public void setCommentMessageId(long commentMessageId) {
        this.commentMessageId = commentMessageId;
    }

    @Id
    @Column(name = "comment_commented_message_id")
    private long commentCommentedMessageId;

    public long getCommentCommentedMessageId() {
        return commentCommentedMessageId;
    }

    public void setCommentCommentedMessageId(long commentCommentedMessageId) {
        this.commentCommentedMessageId = commentCommentedMessageId;
    }
}
