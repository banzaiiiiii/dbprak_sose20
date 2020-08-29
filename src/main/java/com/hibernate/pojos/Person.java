package com.hibernate.pojos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


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
    private String personSpeaks;
    private String personBrowserUsed;
    private String personLocationIp;
    private Collection<Forum> forumsByPersonId;
    private Collection<HasInterest> hasInterestsByPersonId;
    private Collection<HasMember> hasMembersByPersonId;
    private Collection<Knows> knowsByPersonId;
    private Collection<Knows> knowsByPersonId_0;
    private Collection<Likes> likesByPersonId;
    private Collection<Message> messagesByPersonId;
    private City cityByPersonCityId;
    private Collection<StudyAt> studyAtsByPersonId;
    private Collection<WorkAt> workAtsByPersonId;
    private Collection<PersonEmail> personEmails;
    private Collection<PersonSpeaks> languages;

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
    public String getPersonLocationIp()
    {
        return personLocationIp;
    }

    public void setPersonLocationIp(final String personLocationIp)
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
        if (personEmails != null ? !personEmails.equals(person.personEmails) : person.personEmails != null)
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
        result = 31 * result + (personEmails != null ? personEmails.hashCode() : 0);
        result = 31 * result + (personSpeaks != null ? personSpeaks.hashCode() : 0);
        result = 31 * result + (personBrowserUsed != null ? personBrowserUsed.hashCode() : 0);
        result = 31 * result + (personLocationIp != null ? personLocationIp.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "personByForumModeratorPersonId")
    public Collection<Forum> getForumsByPersonId()
    {
        return forumsByPersonId;
    }

    public void setForumsByPersonId(final Collection<Forum> forumsByPersonId)
    {
        this.forumsByPersonId = forumsByPersonId;
    }

    @OneToMany(mappedBy = "personByHasInterestPersonId")
    public Collection<HasInterest> getHasInterestsByPersonId()
    {
        return hasInterestsByPersonId;
    }

    public void setHasInterestsByPersonId(final Collection<HasInterest> hasInterestsByPersonId)
    {
        this.hasInterestsByPersonId = hasInterestsByPersonId;
    }

    @OneToMany(mappedBy = "personByHasMemberPersonId")
    public Collection<HasMember> getHasMembersByPersonId()
    {
        return hasMembersByPersonId;
    }

    public void setHasMembersByPersonId(final Collection<HasMember> hasMembersByPersonId)
    {
        this.hasMembersByPersonId = hasMembersByPersonId;
    }

    @OneToMany(mappedBy = "personByKnowsPersonId")
    public Collection<Knows> getKnowsByPersonId()
    {
        return knowsByPersonId;
    }

    public void setKnowsByPersonId(final Collection<Knows> knowsByPersonId)
    {
        this.knowsByPersonId = knowsByPersonId;
    }

    @OneToMany(mappedBy = "personByKnowsOtherPersonId")
    public Collection<Knows> getKnowsByPersonId_0()
    {
        return knowsByPersonId_0;
    }

    public void setKnowsByPersonId_0(final Collection<Knows> knowsByPersonId_0)
    {
        this.knowsByPersonId_0 = knowsByPersonId_0;
    }

    @OneToMany(mappedBy = "personByLikesPersonId")
    public Collection<Likes> getLikesByPersonId()
    {
        return likesByPersonId;
    }

    public void setLikesByPersonId(final Collection<Likes> likesByPersonId)
    {
        this.likesByPersonId = likesByPersonId;
    }

    @OneToMany(mappedBy = "personByMessagePersonId")
    public Collection<Message> getMessagesByPersonId()
    {
        return messagesByPersonId;
    }

    public void setMessagesByPersonId(final Collection<Message> messagesByPersonId)
    {
        this.messagesByPersonId = messagesByPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "person_city_id", referencedColumnName = "city_id", nullable = false)
    public City getCityByPersonCityId()
    {
        return cityByPersonCityId;
    }

    public void setCityByPersonCityId(final City cityByPersonCityId)
    {
        this.cityByPersonCityId = cityByPersonCityId;
    }

    @OneToMany(mappedBy = "personByStudyAtPersonId")
    public Collection<StudyAt> getStudyAtsByPersonId()
    {
        return studyAtsByPersonId;
    }

    public void setStudyAtsByPersonId(final Collection<StudyAt> studyAtsByPersonId)
    {
        this.studyAtsByPersonId = studyAtsByPersonId;
    }

    @OneToMany(mappedBy = "personByWorkAtPersonId")
    public Collection<WorkAt> getWorkAtsByPersonId()
    {
        return workAtsByPersonId;
    }

    public void setWorkAtsByPersonId(final Collection<WorkAt> workAtsByPersonId)
    {
        this.workAtsByPersonId = workAtsByPersonId;
    }


    @OneToMany(mappedBy = "personByEmailPersonId")
    public Collection<PersonEmail> getEmailsByPersonId()
    {
        return personEmails;
    }

    public void setEmailsByPersonId(final Collection<PersonEmail> personEmails)
    {
        this.personEmails = personEmails;
    }


    @OneToMany(mappedBy = "personByLanguagePersonId")
    public Collection<PersonSpeaks> getLanguagesByPersonId()
    {
        return languages;
    }

    public void setLanguagesByPersonId(final Collection<PersonSpeaks> languages)
    {
        this.languages = languages;
    }

    // custom methods:

    public List<Tag> retrieveTags()
    {
        List<Tag> interests = new ArrayList<>();
        getHasInterestsByPersonId().forEach(e -> interests.add(e.getTagByHasInterestTagId()));
        return interests;
    }

    public List<Company> retrieveCompanies()
    {
        List<Company> companies = new ArrayList<>();
        getWorkAtsByPersonId().forEach(e -> companies.add(e.getCompanyByWorkAtCompanyId()));
        return companies;
    }

    public List<University> retrieveUniversities()
    {
        List<University> universities = new ArrayList<>();
        getStudyAtsByPersonId().forEach(e -> universities.add(e.getUniversityByStudyAtUniversityId()));
        return universities;
    }

    public List<Person> retrieveBiDirFriends()
    {
        List<Person> bidirFriends = new ArrayList<>();
        getKnowsByPersonId().forEach(e -> bidirFriends.add(e.getPersonByKnowsOtherPersonId()));
        getKnowsByPersonId_0().forEach(e -> bidirFriends.add(e.getPersonByKnowsPersonId()));

        return bidirFriends;
    }
}
