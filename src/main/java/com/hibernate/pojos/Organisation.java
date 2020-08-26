package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
// https://stackoverflow.com/questions/190296/how-do-you-effectively-model-inheritance-in-a-database
// https://www.baeldung.com/hibernate-inheritance
// Since we use Table-Per-Type (TPT) inheritance in our database, we have to use 'InheritanceType.JOINED'
@Inheritance(strategy = InheritanceType.JOINED)
public class Organisation
{
    private long organisationId;
    private String organisationName;

    @Id
    @Column(name = "organisation_id")
    public long getOrganisationId()
    {
        return organisationId;
    }

    public void setOrganisationId(final long organisationId)
    {
        this.organisationId = organisationId;
    }

    @Basic
    @Column(name = "organisation_name")
    public String getOrganisationName()
    {
        return organisationName;
    }

    public void setOrganisationName(final String organisationName)
    {
        this.organisationName = organisationName;
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

        final Organisation that = (Organisation) o;

        if (organisationId != that.organisationId)
        {
            return false;
        }
        if (organisationName != null ? !organisationName.equals(that.organisationName) : that.organisationName != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (organisationId ^ (organisationId >>> 32));
        result = 31 * result + (organisationName != null ? organisationName.hashCode() : 0);
        return result;
    }
}
