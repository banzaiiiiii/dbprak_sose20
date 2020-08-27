package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "person_email", schema = "public", catalog = "socialnetwork")
public class PersonEmail
{
    private long id;
    private Person person;
    private String email;
    private Person personByEmailPersonId;

    @Basic
    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    @Id
    @Column(name = "id")
    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "email_person_id", referencedColumnName = "person_id", nullable = false)
    public Person getPersonByEmailPersonId()
    {
        return person;
    }

    public void setPersonByEmailPersonId(final Person emailPersonId)
    {
        this.person = emailPersonId;
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

        final PersonEmail that = (PersonEmail) o;

        if (id != that.id)
        {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
