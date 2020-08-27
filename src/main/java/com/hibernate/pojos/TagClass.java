package com.hibernate.pojos;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tag_class", schema = "public", catalog = "socialnetwork")
public class TagClass
{
    private long tagClassId;
    private String tagClassName;
    private Collection<HasType> hasTypesByTagClassId;
    private Collection<IsSubclassOf> isSubclassOfsByTagClassId;
    private Collection<IsSubclassOf> isSubclassOfsByTagClassId_0;

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

    @OneToMany(mappedBy = "tagClassByTagClassId")
    public Collection<HasType> getHasTypesByTagClassId()
    {
        return hasTypesByTagClassId;
    }

    public void setHasTypesByTagClassId(final Collection<HasType> hasTypesByTagClassId)
    {
        this.hasTypesByTagClassId = hasTypesByTagClassId;
    }

    @OneToMany(mappedBy = "tagClassByTagClassId")
    public Collection<IsSubclassOf> getIsSubclassOfsByTagClassId()
    {
        return isSubclassOfsByTagClassId;
    }

    public void setIsSubclassOfsByTagClassId(final Collection<IsSubclassOf> isSubclassOfsByTagClassId)
    {
        this.isSubclassOfsByTagClassId = isSubclassOfsByTagClassId;
    }

    @OneToMany(mappedBy = "tagClassByTagSuperclassId")
    public Collection<IsSubclassOf> getIsSubclassOfsByTagClassId_0()
    {
        return isSubclassOfsByTagClassId_0;
    }

    public void setIsSubclassOfsByTagClassId_0(final Collection<IsSubclassOf> isSubclassOfsByTagClassId_0)
    {
        this.isSubclassOfsByTagClassId_0 = isSubclassOfsByTagClassId_0;
    }
}
