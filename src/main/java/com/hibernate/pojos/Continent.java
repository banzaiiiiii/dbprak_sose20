package com.hibernate.pojos;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "continent_place_id")
public class Continent extends Place
{
    private long continentId;
    private Long continentPlaceId;
    private Collection<Country> countriesByContinentId;

    @Column(name = "continent_id")
    public long getContinentId()
    {
        return continentId;
    }

    public void setContinentId(final long continentId)
    {
        this.continentId = continentId;
    }

    @Basic
    @Column(name = "continent_place_id", insertable = false, updatable = false)
    public Long getContinentPlaceId()
    {
        return continentPlaceId;
    }

    public void setContinentPlaceId(final Long continentPlaceId)
    {
        this.continentPlaceId = continentPlaceId;
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

        final Continent continent = (Continent) o;

        if (continentId != continent.continentId)
        {
            return false;
        }
        if (continentPlaceId != null ? !continentPlaceId.equals(continent.continentPlaceId) : continent.continentPlaceId != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (continentId ^ (continentId >>> 32));
        result = 31 * result + (continentPlaceId != null ? continentPlaceId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "continentByCountryContinentId")
    public Collection<Country> getCountriesByContinentId()
    {
        return countriesByContinentId;
    }

    public void setCountriesByContinentId(final Collection<Country> countriesByContinentId)
    {
        this.countriesByContinentId = countriesByContinentId;
    }
}
