package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class ForumHasTagPK implements Serializable
{
    private long forumHasTagForumId;
    private long forumHasTagTagId;

    @Column(name = "forum_has_tag_forum_id")
    @Id
    public long getForumHasTagForumId()
    {
        return forumHasTagForumId;
    }

    public void setForumHasTagForumId(final long forumHasTagForumId)
    {
        this.forumHasTagForumId = forumHasTagForumId;
    }

    @Column(name = "forum_has_tag_tag_id")
    @Id
    public long getForumHasTagTagId()
    {
        return forumHasTagTagId;
    }

    public void setForumHasTagTagId(final long forumHasTagTagId)
    {
        this.forumHasTagTagId = forumHasTagTagId;
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

        final ForumHasTagPK that = (ForumHasTagPK) o;

        if (forumHasTagForumId != that.forumHasTagForumId)
        {
            return false;
        }
        if (forumHasTagTagId != that.forumHasTagTagId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (forumHasTagForumId ^ (forumHasTagForumId >>> 32));
        result = 31 * result + (int) (forumHasTagTagId ^ (forumHasTagTagId >>> 32));
        return result;
    }
}
