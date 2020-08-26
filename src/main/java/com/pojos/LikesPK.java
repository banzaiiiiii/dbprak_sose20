package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class LikesPK implements Serializable
{
    private long likesPersonId;
    private long likesMessageId;

    @Column(name = "likes_person_id")
    @Id
    public long getLikesPersonId()
    {
        return likesPersonId;
    }

    public void setLikesPersonId(final long likesPersonId)
    {
        this.likesPersonId = likesPersonId;
    }

    @Column(name = "likes_message_id")
    @Id
    public long getLikesMessageId()
    {
        return likesMessageId;
    }

    public void setLikesMessageId(final long likesMessageId)
    {
        this.likesMessageId = likesMessageId;
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

        final LikesPK likesPK = (LikesPK) o;

        if (likesPersonId != likesPK.likesPersonId)
        {
            return false;
        }
        if (likesMessageId != likesPK.likesMessageId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (likesPersonId ^ (likesPersonId >>> 32));
        result = 31 * result + (int) (likesMessageId ^ (likesMessageId >>> 32));
        return result;
    }
}
