package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class IsSubclassOfPK implements Serializable
{
    private long tagClassId;
    private long tagSuperclassId;

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

    @Column(name = "tag_superclass_id")
    @Id
    public long getTagSuperclassId()
    {
        return tagSuperclassId;
    }

    public void setTagSuperclassId(final long tagSuperclassId)
    {
        this.tagSuperclassId = tagSuperclassId;
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

        final IsSubclassOfPK that = (IsSubclassOfPK) o;

        if (tagClassId != that.tagClassId)
        {
            return false;
        }
        if (tagSuperclassId != that.tagSuperclassId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (tagClassId ^ (tagClassId >>> 32));
        result = 31 * result + (int) (tagSuperclassId ^ (tagSuperclassId >>> 32));
        return result;
    }
}
