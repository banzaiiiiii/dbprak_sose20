package com.hibernate.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Comment
{
    private long commentId;
    private Long commentMessageId;
    private Long commentCommentedMessageId;
    private Message messageByCommentMessageId;
    private Message messageByCommentCommentedMessageId;

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

    @ManyToOne
    @JoinColumn(name = "comment_message_id", referencedColumnName = "message_id")
    public Message getMessageByCommentMessageId()
    {
        return messageByCommentMessageId;
    }

    public void setMessageByCommentMessageId(final Message messageByCommentMessageId)
    {
        this.messageByCommentMessageId = messageByCommentMessageId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_commented_message_id", referencedColumnName = "message_id")
    public Message getMessageByCommentCommentedMessageId()
    {
        return messageByCommentCommentedMessageId;
    }

    public void setMessageByCommentCommentedMessageId(final Message messageByCommentCommentedMessageId)
    {
        this.messageByCommentCommentedMessageId = messageByCommentCommentedMessageId;
    }

    public int countLikes(){
        return getMessageByCommentMessageId().getLikesByMessageId().size();
    }
}
