package com.hibernate.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class MessageHasTagPK implements Serializable
{
    private long messageHasTagMessageId;
    private long messageHasTagTagId;

    @Column(name = "message_has_tag_message_id")
    @Id
    public long getMessageHasTagMessageId()
    {
        return messageHasTagMessageId;
    }

    public void setMessageHasTagMessageId(final long messageHasTagMessageId)
    {
        this.messageHasTagMessageId = messageHasTagMessageId;
    }

    @Column(name = "message_has_tag_tag_id")
    @Id
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

        final MessageHasTagPK that = (MessageHasTagPK) o;

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
