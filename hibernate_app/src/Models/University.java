package Models;


import javax.persistence.*;


@Entity
@Table(name = "university", schema = "public", catalog = "socialnetwork")
public class University {
    private long universityId;

    @Id
    @Column(name = "university_id")
    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    @Id
    @Column(name = "university_organisation_id")
    private long universityOrganisationId;

    public long getUniversityOrganisationId() {
        return universityOrganisationId;
    }

    public void setUniversityOrganisationId(long universityOrganisationId) {
        this.universityOrganisationId = universityOrganisationId;
    }

    @Id
    @Column(name = "university_city_id")
    private long universityCityId;

    public long getUniversityCityId() {
        return universityCityId;
    }

    public void setUniversityCityId(long universityCityId) {
        this.universityCityId = universityCityId;
    }
}
