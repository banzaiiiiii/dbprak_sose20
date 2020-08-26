package com.hibernate.pojos;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Forum
{
    private long forumId;
    private Long forumModeratorPersonId;
    private String forumTitle;
    private Timestamp forumCreationDate;

    @Id
    @Column(name = "forum_id")
    public long getForumId()
    {
        return forumId;
    }

    public void setForumId(final long forumId)
    {
        this.forumId = forumId;
    }

    @Basic
    @Column(name = "forum_moderator_person_id")
    public Long getForumModeratorPersonId()
    {
        return forumModeratorPersonId;
    }

    public void setForumModeratorPersonId(final Long forumModeratorPersonId)
    {
        this.forumModeratorPersonId = forumModeratorPersonId;
    }

    @Basic
    @Column(name = "forum_title")
    public String getForumTitle()
    {
        return forumTitle;
    }

    public void setForumTitle(final String forumTitle)
    {
        this.forumTitle = forumTitle;
    }

    @Basic
    @Column(name = "forum_creation_date")
    public Timestamp getForumCreationDate()
    {
        return forumCreationDate;
    }

    public void setForumCreationDate(final Timestamp forumCreationDate)
    {
        this.forumCreationDate = forumCreationDate;
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

        final Forum forum = (Forum) o;

        if (forumId != forum.forumId)
        {
            return false;
        }
        if (forumModeratorPersonId != null ? !forumModeratorPersonId.equals(forum.forumModeratorPersonId) : forum.forumModeratorPersonId != null)
        {
            return false;
        }
        if (forumTitle != null ? !forumTitle.equals(forum.forumTitle) : forum.forumTitle != null)
        {
            return false;
        }
        if (forumCreationDate != null ? !forumCreationDate.equals(forum.forumCreationDate) : forum.forumCreationDate != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (forumId ^ (forumId >>> 32));
        result = 31 * result + (forumModeratorPersonId != null ? forumModeratorPersonId.hashCode() : 0);
        result = 31 * result + (forumTitle != null ? forumTitle.hashCode() : 0);
        result = 31 * result + (forumCreationDate != null ? forumCreationDate.hashCode() : 0);
        return result;
    }
}
