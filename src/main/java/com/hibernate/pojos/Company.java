package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "company_organisation_id")
public class Company
{
    private long companyId;
    private Long companyOrganisationId;
    private Long companyCountryId;

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

    @Basic
    @Column(name = "company_country_id")
    public Long getCompanyCountryId()
    {
        return companyCountryId;
    }

    public void setCompanyCountryId(final Long companyCountryId)
    {
        this.companyCountryId = companyCountryId;
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
}
