package com.hibernate.pojos;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Tag
{
    private long tagId;
    private String tagName;
    private Collection<ForumHasTag> forumHasTagsByTagId;
    private Collection<HasInterest> hasInterestsByTagId;
    private Collection<HasType> hasTypesByTagId;
    private Collection<MessageHasTag> messageHasTagsByTagId;

    @Id
    @Column(name = "tag_id")
    public long getTagId()
    {
        return tagId;
    }

    public void setTagId(final long tagId)
    {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag_name")
    public String getTagName()
    {
        return tagName;
    }

    public void setTagName(final String tagName)
    {
        this.tagName = tagName;
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

        final Tag tag = (Tag) o;

        if (tagId != tag.tagId)
        {
            return false;
        }
        if (tagName != null ? !tagName.equals(tag.tagName) : tag.tagName != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tagByForumHasTagTagId")
    public Collection<ForumHasTag> getForumHasTagsByTagId()
    {
        return forumHasTagsByTagId;
    }

    public void setForumHasTagsByTagId(final Collection<ForumHasTag> forumHasTagsByTagId)
    {
        this.forumHasTagsByTagId = forumHasTagsByTagId;
    }

    @OneToMany(mappedBy = "tagByHasInterestTagId")
    public Collection<HasInterest> getHasInterestsByTagId()
    {
        return hasInterestsByTagId;
    }

    public void setHasInterestsByTagId(final Collection<HasInterest> hasInterestsByTagId)
    {
        this.hasInterestsByTagId = hasInterestsByTagId;
    }

    @OneToMany(mappedBy = "tagByTagId")
    public Collection<HasType> getHasTypesByTagId()
    {
        return hasTypesByTagId;
    }

    public void setHasTypesByTagId(final Collection<HasType> hasTypesByTagId)
    {
        this.hasTypesByTagId = hasTypesByTagId;
    }

    @OneToMany(mappedBy = "tagByMessageHasTagTagId")
    public Collection<MessageHasTag> getMessageHasTagsByTagId()
    {
        return messageHasTagsByTagId;
    }

    public void setMessageHasTagsByTagId(final Collection<MessageHasTag> messageHasTagsByTagId)
    {
        this.messageHasTagsByTagId = messageHasTagsByTagId;
    }
}
