package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "work_at", schema = "public", catalog = "socialnetwork")
@IdClass(WorkAtPK.class)
public class WorkAt
{
    private long workAtPersonId;
    private long workAtCompanyId;
    private int workAtWorkFrom;

    @Id
    @Column(name = "work_at_person_id")
    public long getWorkAtPersonId()
    {
        return workAtPersonId;
    }

    public void setWorkAtPersonId(final long workAtPersonId)
    {
        this.workAtPersonId = workAtPersonId;
    }

    @Id
    @Column(name = "work_at_company_id")
    public long getWorkAtCompanyId()
    {
        return workAtCompanyId;
    }

    public void setWorkAtCompanyId(final long workAtCompanyId)
    {
        this.workAtCompanyId = workAtCompanyId;
    }

    @Id
    @Column(name = "work_at_work_from")
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

        final WorkAt workAt = (WorkAt) o;

        if (workAtPersonId != workAt.workAtPersonId)
        {
            return false;
        }
        if (workAtCompanyId != workAt.workAtCompanyId)
        {
            return false;
        }
        if (workAtWorkFrom != workAt.workAtWorkFrom)
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
