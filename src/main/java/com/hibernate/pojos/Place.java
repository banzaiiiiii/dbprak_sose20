package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
// https://stackoverflow.com/questions/190296/how-do-you-effectively-model-inheritance-in-a-database
// https://www.baeldung.com/hibernate-inheritance
// Since we use Table-Per-Type (TPT) inheritance in our database, we have to use 'InheritanceType.JOINED'
@Inheritance(strategy = InheritanceType.JOINED)
public class Place
{
    private long placeId;
    private String placeName;

    @Id
    @Column(name = "place_id")
    public long getPlaceId()
    {
        return placeId;
    }

    public void setPlaceId(final long placeId)
    {
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "place_name")
    public String getPlaceName()
    {
        return placeName;
    }

    public void setPlaceName(final String placeName)
    {
        this.placeName = placeName;
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

        final Place place = (Place) o;

        if (placeId != place.placeId)
        {
            return false;
        }
        if (placeName != null ? !placeName.equals(place.placeName) : place.placeName != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (placeId ^ (placeId >>> 32));
        result = 31 * result + (placeName != null ? placeName.hashCode() : 0);
        return result;
    }
}
