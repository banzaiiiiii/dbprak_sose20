package com.pojos;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Message
{
    private long messageId;
    private Long messagePersonId;
    private Long messageCountryId;
    private Date messageCreationDate;
    private String messageBrowserUsed;
    private Object messageLocationIp;
    private String messageContent;
    private int messageLength;

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
    @Column(name = "message_person_id")
    public Long getMessagePersonId()
    {
        return messagePersonId;
    }

    public void setMessagePersonId(final Long messagePersonId)
    {
        this.messagePersonId = messagePersonId;
    }

    @Basic
    @Column(name = "message_country_id")
    public Long getMessageCountryId()
    {
        return messageCountryId;
    }

    public void setMessageCountryId(final Long messageCountryId)
    {
        this.messageCountryId = messageCountryId;
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
    public Object getMessageLocationIp()
    {
        return messageLocationIp;
    }

    public void setMessageLocationIp(final Object messageLocationIp)
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
}
