package com.pojos;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Person
{
    private long personId;
    private long personCityId;
    private Timestamp personCreationDate;
    private String personFirstName;
    private String personLastName;
    private String personGender;
    private Date personBirthday;
    private String personEmail;
    private String personSpeaks;
    private String personBrowserUsed;
    private Object personLocationIp;

    @Id
    @Column(name = "person_id")
    public long getPersonId()
    {
        return personId;
    }

    public void setPersonId(final long personId)
    {
        this.personId = personId;
    }

    @Basic
    @Column(name = "person_city_id")
    public long getPersonCityId()
    {
        return personCityId;
    }

    public void setPersonCityId(final long personCityId)
    {
        this.personCityId = personCityId;
    }

    @Basic
    @Column(name = "person_creation_date")
    public Timestamp getPersonCreationDate()
    {
        return personCreationDate;
    }

    public void setPersonCreationDate(final Timestamp personCreationDate)
    {
        this.personCreationDate = personCreationDate;
    }

    @Basic
    @Column(name = "person_first_name")
    public String getPersonFirstName()
    {
        return personFirstName;
    }

    public void setPersonFirstName(final String personFirstName)
    {
        this.personFirstName = personFirstName;
    }

    @Basic
    @Column(name = "person_last_name")
    public String getPersonLastName()
    {
        return personLastName;
    }

    public void setPersonLastName(final String personLastName)
    {
        this.personLastName = personLastName;
    }

    @Basic
    @Column(name = "person_gender")
    public String getPersonGender()
    {
        return personGender;
    }

    public void setPersonGender(final String personGender)
    {
        this.personGender = personGender;
    }

    @Basic
    @Column(name = "person_birthday")
    public Date getPersonBirthday()
    {
        return personBirthday;
    }

    public void setPersonBirthday(final Date personBirthday)
    {
        this.personBirthday = personBirthday;
    }

    @Basic
    @Column(name = "person_email")
    public String getPersonEmail()
    {
        return personEmail;
    }

    public void setPersonEmail(final String personEmail)
    {
        this.personEmail = personEmail;
    }

    @Basic
    @Column(name = "person_speaks")
    public String getPersonSpeaks()
    {
        return personSpeaks;
    }

    public void setPersonSpeaks(final String personSpeaks)
    {
        this.personSpeaks = personSpeaks;
    }

    @Basic
    @Column(name = "person_browser_used")
    public String getPersonBrowserUsed()
    {
        return personBrowserUsed;
    }

    public void setPersonBrowserUsed(final String personBrowserUsed)
    {
        this.personBrowserUsed = personBrowserUsed;
    }

    @Basic
    @Column(name = "person_location_ip")
    public Object getPersonLocationIp()
    {
        return personLocationIp;
    }

    public void setPersonLocationIp(final Object personLocationIp)
    {
        this.personLocationIp = personLocationIp;
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

        final Person person = (Person) o;

        if (personId != person.personId)
        {
            return false;
        }
        if (personCityId != person.personCityId)
        {
            return false;
        }
        if (personCreationDate != null ? !personCreationDate.equals(person.personCreationDate) : person.personCreationDate != null)
        {
            return false;
        }
        if (personFirstName != null ? !personFirstName.equals(person.personFirstName) : person.personFirstName != null)
        {
            return false;
        }
        if (personLastName != null ? !personLastName.equals(person.personLastName) : person.personLastName != null)
        {
            return false;
        }
        if (personGender != null ? !personGender.equals(person.personGender) : person.personGender != null)
        {
            return false;
        }
        if (personBirthday != null ? !personBirthday.equals(person.personBirthday) : person.personBirthday != null)
        {
            return false;
        }
        if (personEmail != null ? !personEmail.equals(person.personEmail) : person.personEmail != null)
        {
            return false;
        }
        if (personSpeaks != null ? !personSpeaks.equals(person.personSpeaks) : person.personSpeaks != null)
        {
            return false;
        }
        if (personBrowserUsed != null ? !personBrowserUsed.equals(person.personBrowserUsed) : person.personBrowserUsed != null)
        {
            return false;
        }
        if (personLocationIp != null ? !personLocationIp.equals(person.personLocationIp) : person.personLocationIp != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (personId ^ (personId >>> 32));
        result = 31 * result + (int) (personCityId ^ (personCityId >>> 32));
        result = 31 * result + (personCreationDate != null ? personCreationDate.hashCode() : 0);
        result = 31 * result + (personFirstName != null ? personFirstName.hashCode() : 0);
        result = 31 * result + (personLastName != null ? personLastName.hashCode() : 0);
        result = 31 * result + (personGender != null ? personGender.hashCode() : 0);
        result = 31 * result + (personBirthday != null ? personBirthday.hashCode() : 0);
        result = 31 * result + (personEmail != null ? personEmail.hashCode() : 0);
        result = 31 * result + (personSpeaks != null ? personSpeaks.hashCode() : 0);
        result = 31 * result + (personBrowserUsed != null ? personBrowserUsed.hashCode() : 0);
        result = 31 * result + (personLocationIp != null ? personLocationIp.hashCode() : 0);
        return result;
    }
}
