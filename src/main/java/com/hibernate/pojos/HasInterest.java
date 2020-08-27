package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "has_interest", schema = "public", catalog = "socialnetwork")
@IdClass(HasInterestPK.class)
public class HasInterest
{
    private long hasInterestPersonId;
    private long hasInterestTagId;
    private Person personByHasInterestPersonId;
    private Tag tagByHasInterestTagId;

    @Id
    @Column(name = "has_interest_person_id")
    public long getHasInterestPersonId()
    {
        return hasInterestPersonId;
    }

    public void setHasInterestPersonId(final long hasInterestPersonId)
    {
        this.hasInterestPersonId = hasInterestPersonId;
    }

    @Id
    @Column(name = "has_interest_tag_id")
    public long getHasInterestTagId()
    {
        return hasInterestTagId;
    }

    public void setHasInterestTagId(final long hasInterestTagId)
    {
        this.hasInterestTagId = hasInterestTagId;
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

        final HasInterest that = (HasInterest) o;

        if (hasInterestPersonId != that.hasInterestPersonId)
        {
            return false;
        }
        if (hasInterestTagId != that.hasInterestTagId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (hasInterestPersonId ^ (hasInterestPersonId >>> 32));
        result = 31 * result + (int) (hasInterestTagId ^ (hasInterestTagId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "has_interest_person_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)
    public Person getPersonByHasInterestPersonId()
    {
        return personByHasInterestPersonId;
    }

    public void setPersonByHasInterestPersonId(final Person personByHasInterestPersonId)
    {
        this.personByHasInterestPersonId = personByHasInterestPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "has_interest_tag_id", referencedColumnName = "tag_id", nullable = false, insertable = false, updatable = false)
    public Tag getTagByHasInterestTagId()
    {
        return tagByHasInterestTagId;
    }

    public void setTagByHasInterestTagId(final Tag tagByHasInterestTagId)
    {
        this.tagByHasInterestTagId = tagByHasInterestTagId;
    }
}