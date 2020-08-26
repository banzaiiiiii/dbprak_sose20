package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class University
{
    private long universityId;
    private Long universityOrganisationId;
    private Long universityCityId;

    @Id
    @Column(name = "university_id")
    public long getUniversityId()
    {
        return universityId;
    }

    public void setUniversityId(final long universityId)
    {
        this.universityId = universityId;
    }

    @Basic
    @Column(name = "university_organisation_id")
    public Long getUniversityOrganisationId()
    {
        return universityOrganisationId;
    }

    public void setUniversityOrganisationId(final Long universityOrganisationId)
    {
        this.universityOrganisationId = universityOrganisationId;
    }

    @Basic
    @Column(name = "university_city_id")
    public Long getUniversityCityId()
    {
        return universityCityId;
    }

    public void setUniversityCityId(final Long universityCityId)
    {
        this.universityCityId = universityCityId;
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

        final University that = (University) o;

        if (universityId != that.universityId)
        {
            return false;
        }
        if (universityOrganisationId != null ?
            !universityOrganisationId.equals(that.universityOrganisationId) :
            that.universityOrganisationId != null)
        {
            return false;
        }
        if (universityCityId != null ? !universityCityId.equals(that.universityCityId) : that.universityCityId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (universityId ^ (universityId >>> 32));
        result = 31 * result + (universityOrganisationId != null ? universityOrganisationId.hashCode() : 0);
        result = 31 * result + (universityCityId != null ? universityCityId.hashCode() : 0);
        return result;
    }
}
