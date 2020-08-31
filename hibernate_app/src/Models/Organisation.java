package Models;

import javax.persistence.*;

@Entity
@Table(name = "organisation", schema = "public", catalog = "socialnetwork")
public class Organisation {
    private long organisationId;
    private String organisationName;

    @Id
    @Column(name = "organisation_id")
    public long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
    }

    @Basic
    @Column(name = "organisation_name")
    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }
}

