package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class WorkAtPK implements Serializable
{
    private long workAtPersonId;
    private long workAtCompanyId;
    private int workAtWorkFrom;

    @Column(name = "work_at_person_id")
    @Id
    public long getWorkAtPersonId()
    {
        return workAtPersonId;
    }

    public void setWorkAtPersonId(final long workAtPersonId)
    {
        this.workAtPersonId = workAtPersonId;
    }

    @Column(name = "work_at_company_id")
    @Id
    public long getWorkAtCompanyId()
    {
        return workAtCompanyId;
    }

    public void setWorkAtCompanyId(final long workAtCompanyId)
    {
        this.workAtCompanyId = workAtCompanyId;
    }

    @Column(name = "work_at_work_from")
    @Id
    public int getWorkAtWorkFrom()
    {
        return workAtWorkFrom;
    }

    public void setWorkAtWorkFrom(final int workAtWorkFrom)
    {
        this.workAtWorkFrom = workAtWorkFrom;
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

        final WorkAtPK workAtPK = (WorkAtPK) o;

        if (workAtPersonId != workAtPK.workAtPersonId)
        {
            return false;
        }
        if (workAtCompanyId != workAtPK.workAtCompanyId)
        {
            return false;
        }
        if (workAtWorkFrom != workAtPK.workAtWorkFrom)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (workAtPersonId ^ (workAtPersonId >>> 32));
        result = 31 * result + (int) (workAtCompanyId ^ (workAtCompanyId >>> 32));
        result = 31 * result + workAtWorkFrom;
        return result;
    }
}
