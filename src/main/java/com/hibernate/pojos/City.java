package com.hibernate.pojos;

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
@PrimaryKeyJoinColumn(name = "city_place_id")
public class City extends Place
{
    private long cityId;
    private Long cityPlaceId;
    private Country country;

    @OneToMany(mappedBy = "city")
    public Person getPersonen()
    {
        return personen;
    }

    public void setPersonen(final Person personen)
    {
        this.personen = personen;
    }

    private Person personen;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_country_id", referencedColumnName = "country_id")
    public Country getCountry()
    {
        return country;
    }

    public void setCountry(final Country country)
    {
        this.country = country;
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
        if (country != null ? !country.equals(city.country) : city.country != null)
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
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
