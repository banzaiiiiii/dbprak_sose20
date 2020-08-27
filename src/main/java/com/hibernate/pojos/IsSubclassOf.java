package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "is_subclass_of", schema = "public", catalog = "socialnetwork")
@IdClass(IsSubclassOfPK.class)
public class IsSubclassOf
{
    private long tagClassId;
    private long tagSuperclassId;
    private TagClass tagClassByTagClassId;
    private TagClass tagClassByTagSuperclassId;

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

    @Id
    @Column(name = "tag_superclass_id")
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

        final IsSubclassOf that = (IsSubclassOf) o;

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

    @ManyToOne
    @JoinColumn(name = "tag_superclass_id", referencedColumnName = "tag_class_id", nullable = false, insertable = false, updatable = false)
    public TagClass getTagClassByTagSuperclassId()
    {
        return tagClassByTagSuperclassId;
    }

    public void setTagClassByTagSuperclassId(final TagClass tagClassByTagSuperclassId)
    {
        this.tagClassByTagSuperclassId = tagClassByTagSuperclassId;
    }
}
