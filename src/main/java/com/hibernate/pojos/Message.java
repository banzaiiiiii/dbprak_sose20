package com.hibernate.pojos;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Message
{
    private long messageId;
    private Long messagePersonId;
    private Long messageCountryId;
    private Date messageCreationDate;
    private String messageBrowserUsed;
    private String messageLocationIp;
    private String messageContent;
    private int messageLength;
    private Collection<Comment> commentsByMessageId;
    private Collection<Comment> commentsByMessageId_0;
    private Collection<Likes> likesByMessageId;
    private Person personByMessagePersonId;
    private Country countryByMessageCountryId;
    private Collection<MessageHasTag> messageHasTagsByMessageId;
    private Collection<Post> postsByMessageId;

    @Id
    @Column(name = "message_id")
    public long getMessageId()
    {
        return messageId;
    }

    public void setMessageId(final long messageId)
    {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message_creation_date")
    public Date getMessageCreationDate()
    {
        return messageCreationDate;
    }

    public void setMessageCreationDate(final Date messageCreationDate)
    {
        this.messageCreationDate = messageCreationDate;
    }

    @Basic
    @Column(name = "message_browser_used")
    public String getMessageBrowserUsed()
    {
        return messageBrowserUsed;
    }

    public void setMessageBrowserUsed(final String messageBrowserUsed)
    {
        this.messageBrowserUsed = messageBrowserUsed;
    }

    @Basic
    @Column(name = "message_location_ip")
    public String getMessageLocationIp()
    {
        return messageLocationIp;
    }

    public void setMessageLocationIp(final String messageLocationIp)
    {
        this.messageLocationIp = messageLocationIp;
    }

    @Basic
    @Column(name = "message_content")
    public String getMessageContent()
    {
        return messageContent;
    }

    public void setMessageContent(final String messageContent)
    {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "message_length")
    public int getMessageLength()
    {
        return messageLength;
    }

    public void setMessageLength(final int messageLength)
    {
        this.messageLength = messageLength;
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

        final Message message = (Message) o;

        if (messageId != message.messageId)
        {
            return false;
        }
        if (messageLength != message.messageLength)
        {
            return false;
        }
        if (messagePersonId != null ? !messagePersonId.equals(message.messagePersonId) : message.messagePersonId != null)
        {
            return false;
        }
        if (messageCountryId != null ? !messageCountryId.equals(message.messageCountryId) : message.messageCountryId != null)
        {
            return false;
        }
        if (messageCreationDate != null ? !messageCreationDate.equals(message.messageCreationDate) : message.messageCreationDate != null)
        {
            return false;
        }
        if (messageBrowserUsed != null ? !messageBrowserUsed.equals(message.messageBrowserUsed) : message.messageBrowserUsed != null)
        {
            return false;
        }
        if (messageLocationIp != null ? !messageLocationIp.equals(message.messageLocationIp) : message.messageLocationIp != null)
        {
            return false;
        }
        if (messageContent != null ? !messageContent.equals(message.messageContent) : message.messageContent != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (messageId ^ (messageId >>> 32));
        result = 31 * result + (messagePersonId != null ? messagePersonId.hashCode() : 0);
        result = 31 * result + (messageCountryId != null ? messageCountryId.hashCode() : 0);
        result = 31 * result + (messageCreationDate != null ? messageCreationDate.hashCode() : 0);
        result = 31 * result + (messageBrowserUsed != null ? messageBrowserUsed.hashCode() : 0);
        result = 31 * result + (messageLocationIp != null ? messageLocationIp.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + messageLength;
        return result;
    }

    @OneToMany(mappedBy = "messageByCommentMessageId")
    public Collection<Comment> getCommentsByMessageId()
    {
        return commentsByMessageId;
    }

    public void setCommentsByMessageId(final Collection<Comment> commentsByMessageId)
    {
        this.commentsByMessageId = commentsByMessageId;
    }

    @OneToMany(mappedBy = "messageByCommentCommentedMessageId")
    public Collection<Comment> getCommentsByMessageId_0()
    {
        return commentsByMessageId_0;
    }

    public void setCommentsByMessageId_0(final Collection<Comment> commentsByMessageId_0)
    {
        this.commentsByMessageId_0 = commentsByMessageId_0;
    }

    @OneToMany(mappedBy = "messageByLikesMessageId")
    public Collection<Likes> getLikesByMessageId()
    {
        return likesByMessageId;
    }

    public void setLikesByMessageId(final Collection<Likes> likesByMessageId)
    {
        this.likesByMessageId = likesByMessageId;
    }

    @ManyToOne
    @JoinColumn(name = "message_person_id", referencedColumnName = "person_id")
    public Person getPersonByMessagePersonId()
    {
        return personByMessagePersonId;
    }

    public void setPersonByMessagePersonId(final Person personByMessagePersonId)
    {
        this.personByMessagePersonId = personByMessagePersonId;
    }

    @ManyToOne
    @JoinColumn(name = "message_country_id", referencedColumnName = "country_id")
    public Country getCountryByMessageCountryId()
    {
        return countryByMessageCountryId;
    }

    public void setCountryByMessageCountryId(final Country countryByMessageCountryId)
    {
        this.countryByMessageCountryId = countryByMessageCountryId;
    }

    @OneToMany(mappedBy = "messageByMessageHasTagMessageId")
    public Collection<MessageHasTag> getMessageHasTagsByMessageId()
    {
        return messageHasTagsByMessageId;
    }

    public void setMessageHasTagsByMessageId(final Collection<MessageHasTag> messageHasTagsByMessageId)
    {
        this.messageHasTagsByMessageId = messageHasTagsByMessageId;
    }

    @OneToMany(mappedBy = "messageByPostMessageId")
    public Collection<Post> getPostsByMessageId()
    {
        return postsByMessageId;
    }

    public void setPostsByMessageId(final Collection<Post> postsByMessageId)
    {
        this.postsByMessageId = postsByMessageId;
    }
}
