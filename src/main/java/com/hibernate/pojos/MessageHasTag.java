package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "message_has_tag", schema = "public", catalog = "socialnetwork")
@IdClass(MessageHasTagPK.class)
public class MessageHasTag
{
    private long messageHasTagMessageId;
    private long messageHasTagTagId;

    @Id
    @Column(name = "message_has_tag_message_id")
    public long getMessageHasTagMessageId()
    {
        return messageHasTagMessageId;
    }

    public void setMessageHasTagMessageId(final long messageHasTagMessageId)
    {
        this.messageHasTagMessageId = messageHasTagMessageId;
    }

    @Id
    @Column(name = "message_has_tag_tag_id")
    public long getMessageHasTagTagId()
    {
        return messageHasTagTagId;
    }

    public void setMessageHasTagTagId(final long messageHasTagTagId)
    {
        this.messageHasTagTagId = messageHasTagTagId;
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

        final MessageHasTag that = (MessageHasTag) o;

        if (messageHasTagMessageId != that.messageHasTagMessageId)
        {
            return false;
        }
        if (messageHasTagTagId != that.messageHasTagTagId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (messageHasTagMessageId ^ (messageHasTagMessageId >>> 32));
        result = 31 * result + (int) (messageHasTagTagId ^ (messageHasTagTagId >>> 32));
        return result;
    }
}
