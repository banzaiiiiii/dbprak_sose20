package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "pkp_symmetric", schema = "public", catalog = "socialnetwork")
public class PkpSymmetric
{
    private Long knowsPersonId;
    private Long knowsOtherPersonId;

    @Basic
    @Column(name = "knows_person_id")
    public Long getKnowsPersonId()
    {
        return knowsPersonId;
    }

    public void setKnowsPersonId(final Long knowsPersonId)
    {
        this.knowsPersonId = knowsPersonId;
    }

    @Basic
    @Column(name = "knows_other_person_id")
    public Long getKnowsOtherPersonId()
    {
        return knowsOtherPersonId;
    }

    public void setKnowsOtherPersonId(final Long knowsOtherPersonId)
    {
        this.knowsOtherPersonId = knowsOtherPersonId;
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

        final PkpSymmetric that = (PkpSymmetric) o;

        if (knowsPersonId != null ? !knowsPersonId.equals(that.knowsPersonId) : that.knowsPersonId != null)
        {
            return false;
        }
        if (knowsOtherPersonId != null ? !knowsOtherPersonId.equals(that.knowsOtherPersonId) : that.knowsOtherPersonId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = knowsPersonId != null ? knowsPersonId.hashCode() : 0;
        result = 31 * result + (knowsOtherPersonId != null ? knowsOtherPersonId.hashCode() : 0);
        return result;
    }
}
