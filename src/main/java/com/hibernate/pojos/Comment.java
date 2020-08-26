package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Comment
{
    private long commentId;
    private Long commentMessageId;
    private Long commentCommentedMessageId;

    @Id
    @Column(name = "comment_id")
    public long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(final long commentId)
    {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "comment_message_id")
    public Long getCommentMessageId()
    {
        return commentMessageId;
    }

    public void setCommentMessageId(final Long commentMessageId)
    {
        this.commentMessageId = commentMessageId;
    }

    @Basic
    @Column(name = "comment_commented_message_id")
    public Long getCommentCommentedMessageId()
    {
        return commentCommentedMessageId;
    }

    public void setCommentCommentedMessageId(final Long commentCommentedMessageId)
    {
        this.commentCommentedMessageId = commentCommentedMessageId;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        final Comment comment = (Comment) o;

        if (commentId != comment.commentId)
        {
            return false;
        }
        if (commentMessageId != null ? !commentMessageId.equals(comment.commentMessageId) : comment.commentMessageId != null)
        {
            return false;
        }
        if (commentCommentedMessageId != null ?
            !commentCommentedMessageId.equals(comment.commentCommentedMessageId) :
            comment.commentCommentedMessageId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (commentMessageId != null ? commentMessageId.hashCode() : 0);
        result = 31 * result + (commentCommentedMessageId != null ? commentCommentedMessageId.hashCode() : 0);
        return result;
    }
}
