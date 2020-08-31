package Models;

import javax.persistence.*;

@Entity
@Table(name = "continent", schema = "public", catalog = "socialnetwork")
public class Continent {
    private long continentId;

    @Id
    @Column(name = "continent_id", table = "continent")
    public long getContinentId() {
        return continentId;
    }

    public void setContinentId(long continentId) {
        this.continentId = continentId;
    }

    private Long continentPlaceId;

    @Basic
    @Column(name = "continent_place_id", table = "continent")
    public Long getContinentPlaceId() {
        return continentPlaceId;
    }

    public void setContinentPlaceId(Long continentPlaceId) {
        this.continentPlaceId = continentPlaceId;
    }


}
