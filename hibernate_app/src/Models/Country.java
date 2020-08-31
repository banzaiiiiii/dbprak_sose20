package Models;

import javax.persistence.*;

@Entity
@Table(name = "country", schema = "public", catalog = "socialnetwork")
public class Country {

    private long countryId;

    @Id
    @Column(name = "country_id", table = "country")
    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }


    private Long countryPlaceId;

    @Basic
    @Column(name = "country_place_id", table = "country")
    public Long getCountryPlaceId() {
        return countryPlaceId;
    }

    public void setCountryPlaceId(Long countryPlaceId) {
        this.countryPlaceId = countryPlaceId;
    }

    private Long countryContinentId;

    @Basic
    @Column(name = "country_continent_id", table = "country")
    public Long getCountryContinentId() {
        return countryContinentId;
    }

    public void setCountryContinentId(Long countryContinentId) {
        this.countryContinentId = countryContinentId;
    }


}
