package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "person_speaks", schema = "public", catalog = "socialnetwork")
public class PersonSpeaks
{
    private int id;
    private String language;
    private Person personByLanguagePersonId;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(final String language)
    {
        this.language = language;
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

        final PersonSpeaks that = (PersonSpeaks) o;

        if (id != that.id)
        {
            return false;
        }
        if (language != null ? !language.equals(that.language) : that.language != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "language_person_id", referencedColumnName = "person_id")
    public Person getPersonByLanguagePersonId()
    {
        return personByLanguagePersonId;
    }

    public void setPersonByLanguagePersonId(final Person personByLanguagePersonId)
    {
        this.personByLanguagePersonId = personByLanguagePersonId;
    }
}
