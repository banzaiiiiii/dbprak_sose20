package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class City
{
    private long cityId;
    private Long cityPlaceId;
    private Long cityCountryId;

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

    @Basic
    @Column(name = "city_country_id")
    public Long getCityCountryId()
    {
        return cityCountryId;
    }

    public void setCityCountryId(final Long cityCountryId)
    {
        this.cityCountryId = cityCountryId;
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
}
