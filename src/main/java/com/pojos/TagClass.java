package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tag_class", schema = "public", catalog = "socialnetwork")
public class TagClass
{
    private long tagClassId;
    private String tagClassName;

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

    @Basic
    @Column(name = "tag_class_name")
    public String getTagClassName()
    {
        return tagClassName;
    }

    public void setTagClassName(final String tagClassName)
    {
        this.tagClassName = tagClassName;
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

        final TagClass tagClass = (TagClass) o;

        if (tagClassId != tagClass.tagClassId)
        {
            return false;
        }
        if (tagClassName != null ? !tagClassName.equals(tagClass.tagClassName) : tagClass.tagClassName != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (tagClassId ^ (tagClassId >>> 32));
        result = 31 * result + (tagClassName != null ? tagClassName.hashCode() : 0);
        return result;
    }
}
