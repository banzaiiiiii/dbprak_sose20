package Models;


import javax.persistence.*;


@Entity
@Table(name = "place", schema = "public", catalog = "socialnetwork")
public class Place {
    private long placeId;

    @Id
    @Column(name = "place_id", table = "place")
    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    private String placeName;

    @Column(name = "place_name", table = "place")
    public String getPlaceName(){
        return placeName;
    }

    public void setPlaceName(String placeName){
        this.placeName = placeName;
    }


}
