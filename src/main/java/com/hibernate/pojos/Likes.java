package com.hibernate.pojos;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@IdClass(LikesPK.class)
public class Likes
{
    private long likesPersonId;
    private long likesMessageId;
    private Date likesCreationDate;
    private Person personByLikesPersonId;
    private Message messageByLikesMessageId;

    @Id
    @Column(name = "likes_person_id")
    public long getLikesPersonId()
    {
        return likesPersonId;
    }

    public void setLikesPersonId(final long likesPersonId)
    {
        this.likesPersonId = likesPersonId;
    }

    @Id
    @Column(name = "likes_message_id")
    public long getLikesMessageId()
    {
        return likesMessageId;
    }

    public void setLikesMessageId(final long likesMessageId)
    {
        this.likesMessageId = likesMessageId;
    }

    @Basic
    @Column(name = "likes_creation_date")
    public Date getLikesCreationDate()
    {
        return likesCreationDate;
    }

    public void setLikesCreationDate(final Date likesCreationDate)
    {
        this.likesCreationDate = likesCreationDate;
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

        final Likes likes = (Likes) o;

        if (likesPersonId != likes.likesPersonId)
        {
            return false;
        }
        if (likesMessageId != likes.likesMessageId)
        {
            return false;
        }
        if (likesCreationDate != null ? !likesCreationDate.equals(likes.likesCreationDate) : likes.likesCreationDate != null)
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
        result = 31 * result + (likesCreationDate != null ? likesCreationDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "likes_person_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)
    public Person getPersonByLikesPersonId()
    {
        return personByLikesPersonId;
    }

    public void setPersonByLikesPersonId(final Person personByLikesPersonId)
    {
        this.personByLikesPersonId = personByLikesPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "likes_message_id", referencedColumnName = "message_id", nullable = false, insertable = false, updatable = false)
    public Message getMessageByLikesMessageId()
    {
        return messageByLikesMessageId;
    }

    public void setMessageByLikesMessageId(final Message messageByLikesMessageId)
    {
        this.messageByLikesMessageId = messageByLikesMessageId;
    }
}
