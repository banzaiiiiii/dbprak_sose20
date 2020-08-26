package com.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Continent
{
    private long continentId;
    private Long continentPlaceId;

    @Id
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
    @Column(name = "continent_place_id")
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
}
