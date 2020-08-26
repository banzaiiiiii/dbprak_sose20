package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class HasInterestPK implements Serializable
{
    private long hasInterestPersonId;
    private long hasInterestTagId;

    @Column(name = "has_interest_person_id")
    @Id
    public long getHasInterestPersonId()
    {
        return hasInterestPersonId;
    }

    public void setHasInterestPersonId(final long hasInterestPersonId)
    {
        this.hasInterestPersonId = hasInterestPersonId;
    }

    @Column(name = "has_interest_tag_id")
    @Id
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

        final HasInterestPK that = (HasInterestPK) o;

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
}
