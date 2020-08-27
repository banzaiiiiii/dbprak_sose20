package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "forum_has_tag", schema = "public", catalog = "socialnetwork")
@IdClass(ForumHasTagPK.class)
public class ForumHasTag
{
    private long forumHasTagForumId;
    private long forumHasTagTagId;
    private Forum forumByForumHasTagForumId;
    private Tag tagByForumHasTagTagId;

    @Id
    @Column(name = "forum_has_tag_forum_id")
    public long getForumHasTagForumId()
    {
        return forumHasTagForumId;
    }

    public void setForumHasTagForumId(final long forumHasTagForumId)
    {
        this.forumHasTagForumId = forumHasTagForumId;
    }

    @Id
    @Column(name = "forum_has_tag_tag_id")
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

        final ForumHasTag that = (ForumHasTag) o;

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

    @ManyToOne
    @JoinColumn(name = "forum_has_tag_forum_id", referencedColumnName = "forum_id", nullable = false, insertable = false, updatable = false)
    public Forum getForumByForumHasTagForumId()
    {
        return forumByForumHasTagForumId;
    }

    public void setForumByForumHasTagForumId(final Forum forumByForumHasTagForumId)
    {
        this.forumByForumHasTagForumId = forumByForumHasTagForumId;
    }

    @ManyToOne
    @JoinColumn(name = "forum_has_tag_tag_id", referencedColumnName = "tag_id", nullable = false, insertable = false, updatable = false)
    public Tag getTagByForumHasTagTagId()
    {
        return tagByForumHasTagTagId;
    }

    public void setTagByForumHasTagTagId(final Tag tagByForumHasTagTagId)
    {
        this.tagByForumHasTagTagId = tagByForumHasTagTagId;
    }
}
