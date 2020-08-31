package Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "person", schema = "public", catalog = "socialnetwork")
public class Person {
    private Long personId;
    private Timestamp personCreationDate;
    private String personFirstName;
    private String personLastName;
    private String personGender;
    private Date personBirthday;
    private String personBrowserUsed;
    private Object personLocationIp;

    @Id
    @Column(name = "person_id")
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "person_creation_date")
    public Timestamp getPersonCreationDate() {
        return personCreationDate;
    }

    public void setPersonCreationDate(Timestamp personCreationDate) {
        this.personCreationDate = personCreationDate;
    }

    @Basic
    @Column(name = "person_first_name")
    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    @Basic
    @Column(name = "person_last_name")
    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    @Basic
    @Column(name = "person_gender")
    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    @Basic
    @Column(name = "person_birthday")
    public Date getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    @Basic
    @Column(name = "person_browser_used")
    public String getPersonBrowserUsed() {
        return personBrowserUsed;
    }

    public void setPersonBrowserUsed(String personBrowserUsed) {
        this.personBrowserUsed = personBrowserUsed;
    }

    @Basic
    @Column(name = "person_location_ip")
    public Object getPersonLocationIp() {
        return personLocationIp;
    }

    public void setPersonLocationIp(Object personLocationIp) {
        this.personLocationIp = personLocationIp;
    }
}
