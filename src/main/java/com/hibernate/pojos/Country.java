package com.hibernate.pojos;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "country_place_id")
public class Country extends Place
{
    private long countryId;
    private Long countryPlaceId;
    private Long countryContinentId;
    private Collection<City> citiesByCountryId;
    private Collection<Company> companiesByCountryId;
    private Continent continentByCountryContinentId;
    private Collection<Message> messagesByCountryId;

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
    @Column(name = "country_place_id", insertable = false, updatable = false)
    public Long getCountryPlaceId()
    {
        return countryPlaceId;
    }

    public void setCountryPlaceId(final Long countryPlaceId)
    {
        this.countryPlaceId = countryPlaceId;
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

    @OneToMany(mappedBy = "countryByCityCountryId")
    public Collection<City> getCitiesByCountryId()
    {
        return citiesByCountryId;
    }

    public void setCitiesByCountryId(final Collection<City> citiesByCountryId)
    {
        this.citiesByCountryId = citiesByCountryId;
    }

    @OneToMany(mappedBy = "countryByCompanyCountryId")
    public Collection<Company> getCompaniesByCountryId()
    {
        return companiesByCountryId;
    }

    public void setCompaniesByCountryId(final Collection<Company> companiesByCountryId)
    {
        this.companiesByCountryId = companiesByCountryId;
    }

    @ManyToOne
    @JoinColumn(name = "country_continent_id", referencedColumnName = "continent_id")
    public Continent getContinentByCountryContinentId()
    {
        return continentByCountryContinentId;
    }

    public void setContinentByCountryContinentId(final Continent continentByCountryContinentId)
    {
        this.continentByCountryContinentId = continentByCountryContinentId;
    }

    @OneToMany(mappedBy = "countryByMessageCountryId")
    public Collection<Message> getMessagesByCountryId()
    {
        return messagesByCountryId;
    }

    public void setMessagesByCountryId(final Collection<Message> messagesByCountryId)
    {
        this.messagesByCountryId = messagesByCountryId;
    }
}
