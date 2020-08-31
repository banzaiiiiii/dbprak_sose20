package Models;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "person_email", schema = "public", catalog = "socialnetwork")
public class PersonEmail {
    private Integer id;
    private String email;


    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
