package com.hibernate.pojos;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(KnowsPK.class)
public class Knows
{
    private long knowsPersonId;
    private long knowsOtherPersonId;
    private Timestamp knowsCreationDate;

    @Id
    @Column(name = "knows_person_id")
    public long getKnowsPersonId()
    {
        return knowsPersonId;
    }

    public void setKnowsPersonId(final long knowsPersonId)
    {
        this.knowsPersonId = knowsPersonId;
    }

    @Id
    @Column(name = "knows_other_person_id")
    public long getKnowsOtherPersonId()
    {
        return knowsOtherPersonId;
    }

    public void setKnowsOtherPersonId(final long knowsOtherPersonId)
    {
        this.knowsOtherPersonId = knowsOtherPersonId;
    }

    @Basic
    @Column(name = "knows_creation_date")
    public Timestamp getKnowsCreationDate()
    {
        return knowsCreationDate;
    }

    public void setKnowsCreationDate(final Timestamp knowsCreationDate)
    {
        this.knowsCreationDate = knowsCreationDate;
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

        final Knows knows = (Knows) o;

        if (knowsPersonId != knows.knowsPersonId)
        {
            return false;
        }
        if (knowsOtherPersonId != knows.knowsOtherPersonId)
        {
            return false;
        }
        if (knowsCreationDate != null ? !knowsCreationDate.equals(knows.knowsCreationDate) : knows.knowsCreationDate != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (knowsPersonId ^ (knowsPersonId >>> 32));
        result = 31 * result + (int) (knowsOtherPersonId ^ (knowsOtherPersonId >>> 32));
        result = 31 * result + (knowsCreationDate != null ? knowsCreationDate.hashCode() : 0);
        return result;
    }
}
