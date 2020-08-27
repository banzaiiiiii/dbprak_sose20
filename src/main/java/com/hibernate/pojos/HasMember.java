package com.hibernate.pojos;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "has_member", schema = "public", catalog = "socialnetwork")
@IdClass(HasMemberPK.class)
public class HasMember
{
    private long hasMemberForumId;
    private long hasMemberPersonId;
    private Date hasMemberJoinDate;
    private Forum forumByHasMemberForumId;
    private Person personByHasMemberPersonId;

    @Id
    @Column(name = "has_member_forum_id")
    public long getHasMemberForumId()
    {
        return hasMemberForumId;
    }

    public void setHasMemberForumId(final long hasMemberForumId)
    {
        this.hasMemberForumId = hasMemberForumId;
    }

    @Id
    @Column(name = "has_member_person_id")
    public long getHasMemberPersonId()
    {
        return hasMemberPersonId;
    }

    public void setHasMemberPersonId(final long hasMemberPersonId)
    {
        this.hasMemberPersonId = hasMemberPersonId;
    }

    @Basic
    @Column(name = "has_member_join_date")
    public Date getHasMemberJoinDate()
    {
        return hasMemberJoinDate;
    }

    public void setHasMemberJoinDate(final Date hasMemberJoinDate)
    {
        this.hasMemberJoinDate = hasMemberJoinDate;
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

        final HasMember hasMember = (HasMember) o;

        if (hasMemberForumId != hasMember.hasMemberForumId)
        {
            return false;
        }
        if (hasMemberPersonId != hasMember.hasMemberPersonId)
        {
            return false;
        }
        if (hasMemberJoinDate != null ? !hasMemberJoinDate.equals(hasMember.hasMemberJoinDate) : hasMember.hasMemberJoinDate != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (hasMemberForumId ^ (hasMemberForumId >>> 32));
        result = 31 * result + (int) (hasMemberPersonId ^ (hasMemberPersonId >>> 32));
        result = 31 * result + (hasMemberJoinDate != null ? hasMemberJoinDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "has_member_forum_id", referencedColumnName = "forum_id", nullable = false, insertable = false, updatable = false)
    public Forum getForumByHasMemberForumId()
    {
        return forumByHasMemberForumId;
    }

    public void setForumByHasMemberForumId(final Forum forumByHasMemberForumId)
    {
        this.forumByHasMemberForumId = forumByHasMemberForumId;
    }

    @ManyToOne
    @JoinColumn(name = "has_member_person_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)
    public Person getPersonByHasMemberPersonId()
    {
        return personByHasMemberPersonId;
    }

    public void setPersonByHasMemberPersonId(final Person personByHasMemberPersonId)
    {
        this.personByHasMemberPersonId = personByHasMemberPersonId;
    }
}
