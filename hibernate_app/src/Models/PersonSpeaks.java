package Models;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "person_speaks", schema = "public", catalog = "socialnetwork")
public class PersonSpeaks {
    private Integer id;
    private String language;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
