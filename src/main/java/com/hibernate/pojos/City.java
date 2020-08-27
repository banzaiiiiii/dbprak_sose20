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
@PrimaryKeyJoinColumn(name = "city_place_id")
public class City extends Place
{
    private long cityId;
    private Long cityPlaceId;
    private Long cityCountryId;
    private Country countryByCityCountryId;
    private Collection<Person> peopleByCityId;
    private Collection<University> universitiesByCityId;

    @Id
    @Column(name = "city_id")
    public long getCityId()
    {
        return cityId;
    }

    public void setCityId(final long cityId)
    {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_place_id")
    public Long getCityPlaceId()
    {
        return cityPlaceId;
    }

    public void setCityPlaceId(final Long cityPlaceId)
    {
        this.cityPlaceId = cityPlaceId;
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

        final City city = (City) o;

        if (cityId != city.cityId)
        {
            return false;
        }
        if (cityPlaceId != null ? !cityPlaceId.equals(city.cityPlaceId) : city.cityPlaceId != null)
        {
            return false;
        }
        if (cityCountryId != null ? !cityCountryId.equals(city.cityCountryId) : city.cityCountryId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (cityId ^ (cityId >>> 32));
        result = 31 * result + (cityPlaceId != null ? cityPlaceId.hashCode() : 0);
        result = 31 * result + (cityCountryId != null ? cityCountryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "city_country_id", referencedColumnName = "country_id")
    public Country getCountryByCityCountryId()
    {
        return countryByCityCountryId;
    }

    public void setCountryByCityCountryId(final Country countryByCityCountryId)
    {
        this.countryByCityCountryId = countryByCityCountryId;
    }

    @OneToMany(mappedBy = "cityByPersonCityId")
    public Collection<Person> getPeopleByCityId()
    {
        return peopleByCityId;
    }

    public void setPeopleByCityId(final Collection<Person> peopleByCityId)
    {
        this.peopleByCityId = peopleByCityId;
    }

    @OneToMany(mappedBy = "cityByUniversityCityId")
    public Collection<University> getUniversitiesByCityId()
    {
        return universitiesByCityId;
    }

    public void setUniversitiesByCityId(final Collection<University> universitiesByCityId)
    {
        this.universitiesByCityId = universitiesByCityId;
    }
}
