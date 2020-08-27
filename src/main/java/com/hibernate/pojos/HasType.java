package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "has_type", schema = "public", catalog = "socialnetwork")
@IdClass(HasTypePK.class)
public class HasType
{
    private long tagId;
    private long tagClassId;
    private Tag tagByTagId;
    private TagClass tagClassByTagClassId;

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

    @Id
    @Column(name = "tag_class_id")
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

        final HasType hasType = (HasType) o;

        if (tagId != hasType.tagId)
        {
            return false;
        }
        if (tagClassId != hasType.tagClassId)
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

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", nullable = false, insertable = false, updatable = false)
    public Tag getTagByTagId()
    {
        return tagByTagId;
    }

    public void setTagByTagId(final Tag tagByTagId)
    {
        this.tagByTagId = tagByTagId;
    }

    @ManyToOne
    @JoinColumn(name = "tag_class_id", referencedColumnName = "tag_class_id", nullable = false, insertable = false, updatable = false)
    public TagClass getTagClassByTagClassId()
    {
        return tagClassByTagClassId;
    }

    public void setTagClassByTagClassId(final TagClass tagClassByTagClassId)
    {
        this.tagClassByTagClassId = tagClassByTagClassId;
    }
}