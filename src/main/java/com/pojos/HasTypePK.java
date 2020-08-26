package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class HasTypePK implements Serializable
{
    private long tagId;
    private long tagClassId;

    @Column(name = "tag_id")
    @Id
    public long getTagId()
    {
        return tagId;
    }

    public void setTagId(final long tagId)
    {
        this.tagId = tagId;
    }

    @Column(name = "tag_class_id")
    @Id
    public long getTagClassId()
    {
        return tagClassId;
    }

    public void setTagClassId(final long tagClassId)
    {
        this.tagClassId = tagClassId;
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

        final HasTypePK hasTypePK = (HasTypePK) o;

        if (tagId != hasTypePK.tagId)
        {
            return false;
        }
        if (tagClassId != hasTypePK.tagClassId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (int) (tagClassId ^ (tagClassId >>> 32));
        return result;
    }
}
