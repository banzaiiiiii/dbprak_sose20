package com.hibernate.pojos;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Forum
{
    private long forumId;
    private Long forumModeratorPersonId;
    private String forumTitle;
    private Timestamp forumCreationDate;
    private Person personByForumModeratorPersonId;
    private Collection<ForumHasTag> forumHasTagsByForumId;
    private Collection<HasMember> hasMembersByForumId;
    private Collection<Post> postsByForumId;

    @Id
    @Column(name = "forum_id")
    public long getForumId()
    {
        return forumId;
    }

    public void setForumId(final long forumId)
    {
        this.forumId = forumId;
    }

    @Basic
    @Column(name = "forum_title")
    public String getForumTitle()
    {
        return forumTitle;
    }

    public void setForumTitle(final String forumTitle)
    {
        this.forumTitle = forumTitle;
    }

    @Basic
    @Column(name = "forum_creation_date")
    public Timestamp getForumCreationDate()
    {
        return forumCreationDate;
    }

    public void setForumCreationDate(final Timestamp forumCreationDate)
    {
        this.forumCreationDate = forumCreationDate;
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

        final Forum forum = (Forum) o;

        if (forumId != forum.forumId)
        {
            return false;
        }
        if (forumModeratorPersonId != null ? !forumModeratorPersonId.equals(forum.forumModeratorPersonId) : forum.forumModeratorPersonId != null)
        {
            return false;
        }
        if (forumTitle != null ? !forumTitle.equals(forum.forumTitle) : forum.forumTitle != null)
        {
            return false;
        }
        if (forumCreationDate != null ? !forumCreationDate.equals(forum.forumCreationDate) : forum.forumCreationDate != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (forumId ^ (forumId >>> 32));
        result = 31 * result + (forumModeratorPersonId != null ? forumModeratorPersonId.hashCode() : 0);
        result = 31 * result + (forumTitle != null ? forumTitle.hashCode() : 0);
        result = 31 * result + (forumCreationDate != null ? forumCreationDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "forum_moderator_person_id", referencedColumnName = "person_id")
    public Person getPersonByForumModeratorPersonId()
    {
        return personByForumModeratorPersonId;
    }

    public void setPersonByForumModeratorPersonId(final Person personByForumModeratorPersonId)
    {
        this.personByForumModeratorPersonId = personByForumModeratorPersonId;
    }

    @OneToMany(mappedBy = "forumByForumHasTagForumId")
    public Collection<ForumHasTag> getForumHasTagsByForumId()
    {
        return forumHasTagsByForumId;
    }

    public void setForumHasTagsByForumId(final Collection<ForumHasTag> forumHasTagsByForumId)
    {
        this.forumHasTagsByForumId = forumHasTagsByForumId;
    }

    @OneToMany(mappedBy = "forumByHasMemberForumId")
    public Collection<HasMember> getHasMembersByForumId()
    {
        return hasMembersByForumId;
    }

    public void setHasMembersByForumId(final Collection<HasMember> hasMembersByForumId)
    {
        this.hasMembersByForumId = hasMembersByForumId;
    }

    @OneToMany(mappedBy = "forumByPostForumId")
    public Collection<Post> getPostsByForumId()
    {
        return postsByForumId;
    }

    public void setPostsByForumId(final Collection<Post> postsByForumId)
    {
        this.postsByForumId = postsByForumId;
    }
}
