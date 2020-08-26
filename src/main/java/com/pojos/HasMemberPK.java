package com.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class HasMemberPK implements Serializable
{
    private long hasMemberForumId;
    private long hasMemberPersonId;

    @Column(name = "has_member_forum_id")
    @Id
    public long getHasMemberForumId()
    {
        return hasMemberForumId;
    }

    public void setHasMemberForumId(final long hasMemberForumId)
    {
        this.hasMemberForumId = hasMemberForumId;
    }

    @Column(name = "has_member_person_id")
    @Id
    public long getHasMemberPersonId()
    {
        return hasMemberPersonId;
    }

    public void setHasMemberPersonId(final long hasMemberPersonId)
    {
        this.hasMemberPersonId = hasMemberPersonId;
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

        final HasMemberPK that = (HasMemberPK) o;

        if (hasMemberForumId != that.hasMemberForumId)
        {
            return false;
        }
        if (hasMemberPersonId != that.hasMemberPersonId)
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
        return result;
    }
}
