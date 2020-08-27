package com.hibernate.pojos;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "company_organisation_id")
public class Company extends Organisation
{
    private long companyId;
    private Long companyOrganisationId;
    private Long companyCountryId;
    private Organisation organisationByCompanyOrganisationId;
    private Country countryByCompanyCountryId;
    private Collection<WorkAt> workAtsByCompanyId;

    @Id
    @Column(name = "company_id")
    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(final long companyId)
    {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "company_organisation_id")
    public Long getCompanyOrganisationId()
    {
        return companyOrganisationId;
    }

    public void setCompanyOrganisationId(final Long companyOrganisationId)
    {
        this.companyOrganisationId = companyOrganisationId;
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

        final Company company = (Company) o;

        if (companyId != company.companyId)
        {
            return false;
        }
        if (companyOrganisationId != null ? !companyOrganisationId.equals(company.companyOrganisationId) : company.companyOrganisationId != null)
        {
            return false;
        }
        if (companyCountryId != null ? !companyCountryId.equals(company.companyCountryId) : company.companyCountryId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (companyOrganisationId != null ? companyOrganisationId.hashCode() : 0);
        result = 31 * result + (companyCountryId != null ? companyCountryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "company_country_id", referencedColumnName = "country_id")
    public Country getCountryByCompanyCountryId()
    {
        return countryByCompanyCountryId;
    }

    public void setCountryByCompanyCountryId(final Country countryByCompanyCountryId)
    {
        this.countryByCompanyCountryId = countryByCompanyCountryId;
    }

    @OneToMany(mappedBy = "companyByWorkAtCompanyId")
    public Collection<WorkAt> getWorkAtsByCompanyId()
    {
        return workAtsByCompanyId;
    }

    public void setWorkAtsByCompanyId(final Collection<WorkAt> workAtsByCompanyId)
    {
        this.workAtsByCompanyId = workAtsByCompanyId;
    }
}
