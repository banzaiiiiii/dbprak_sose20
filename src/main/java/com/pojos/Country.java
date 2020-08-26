package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Country
{
    private long countryId;
    private Long countryPlaceId;
    private Long countryContinentId;

    @Id
    @Column(name = "country_id")
    public long getCountryId()
    {
        return countryId;
    }

    public void setCountryId(final long countryId)
    {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "country_place_id")
    public Long getCountryPlaceId()
    {
        return countryPlaceId;
    }

    public void setCountryPlaceId(final Long countryPlaceId)
    {
        this.countryPlaceId = countryPlaceId;
    }

    @Basic
    @Column(name = "country_continent_id")
    public Long getCountryContinentId()
    {
        return countryContinentId;
    }

    public void setCountryContinentId(final Long countryContinentId)
    {
        this.countryContinentId = countryContinentId;
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

        final Country country = (Country) o;

        if (countryId != country.countryId)
        {
            return false;
        }
        if (countryPlaceId != null ? !countryPlaceId.equals(country.countryPlaceId) : country.countryPlaceId != null)
        {
            return false;
        }
        if (countryContinentId != null ? !countryContinentId.equals(country.countryContinentId) : country.countryContinentId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (countryId ^ (countryId >>> 32));
        result = 31 * result + (countryPlaceId != null ? countryPlaceId.hashCode() : 0);
        result = 31 * result + (countryContinentId != null ? countryContinentId.hashCode() : 0);
        return result;
    }
}
