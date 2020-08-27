package com.hibernate.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "country_place_id") // falls das nicht klappt "country_id" probieren
public class Country extends Place
{
    private long countryId;
    private Long countryPlaceId;
    private Continent continent;
    private List<City> cities = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    public List<City> getCities()
    {
        return cities;
    }

    public void setCities(final List<City> cities)
    {
        this.cities = cities;
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_continent_id", referencedColumnName = "continent_id")
    public Continent getContinent()
    {
        return continent;
    }

    public void setContinent(final Continent continent)
    {
        this.continent = continent;
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
        if (continent != null ? !continent.equals(country.continent) : country.continent != null)
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
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        return result;
    }
}
