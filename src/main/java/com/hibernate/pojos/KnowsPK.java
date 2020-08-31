package com.hibernate.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class KnowsPK implements Serializable
{
    private long knowsPersonId;
    private long knowsOtherPersonId;

    @Column(name = "knows_person_id")
    @Id
    public long getKnowsPersonId()
    {
        return knowsPersonId;
    }

    public void setKnowsPersonId(final long knowsPersonId)
    {
        this.knowsPersonId = knowsPersonId;
    }

    @Column(name = "knows_other_person_id")
    @Id
    public long getKnowsOtherPersonId()
    {
        return knowsOtherPersonId;
    }

    public void setKnowsOtherPersonId(final long knowsOtherPersonId)
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

        final KnowsPK knowsPK = (KnowsPK) o;

        if (knowsPersonId != knowsPK.knowsPersonId)
        {
            return false;
        }
        if (knowsOtherPersonId != knowsPK.knowsOtherPersonId)
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
        return result;
    }
}
