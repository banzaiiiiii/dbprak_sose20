package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Tag
{
    private long tagId;
    private String tagName;

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
}
