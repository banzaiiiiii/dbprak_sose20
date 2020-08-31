package Models;

import javax.persistence.*;


@Entity
@Table(name = "city", schema = "public", catalog = "socialnetwork")
public class City {

    private long cityId;

    @Id
    @Column(name = "city_id", table = "city", unique = true)
    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }


    private Long cityPlaceId;


    @Column(name = "city_place_id", table = "city")
    public Long getCityPlaceId() {
        return cityPlaceId;
    }

    public void setCityPlaceId(Long cityPlaceId) {
        this.cityPlaceId = cityPlaceId;
    }

    private Long cityCountryId;


    @Column(name = "city_country_id", table = "city")
    public Long getCityCountryId() {
        return cityCountryId;
    }

    public void setCityCountryId(Long cityCountryId) {
        this.cityCountryId = cityCountryId;
    }


}
