package Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table(name = "message", schema = "public", catalog = "socialnetwork")
public class Message {
    private Long messageId;
    private Date messageCreationDate;
    private String messageBrowserUsed;
    private Object messageLocationIp;
    private String messageContent;
    private Integer messageLength;
    private Long messagePersonId;
    private Long messageCountryId;


    @Id
    @Column(name = "message_id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Column(name = "message_Person_Id")
    public Long getMessagePersonId() {
        return messagePersonId;
    }

    public void setMessagePersonId(Long messagePersonId) {
        this.messagePersonId = messagePersonId;
    }

    @Column(name = "messageCountryId")
    public Long getMessageCountryId() {
        return messageCountryId;
    }

    public void setMessageCountryId(Long messageCountryId) {
        this.messageCountryId = messageCountryId;
    }




    @Basic
    @Column(name = "message_creation_date")
    public Date getMessageCreationDate() {
        return messageCreationDate;
    }

    public void setMessageCreationDate(Date messageCreationDate) {
        this.messageCreationDate = messageCreationDate;
    }

    @Basic
    @Column(name = "message_browser_used")
    public String getMessageBrowserUsed() {
        return messageBrowserUsed;
    }

    public void setMessageBrowserUsed(String messageBrowserUsed) {
        this.messageBrowserUsed = messageBrowserUsed;
    }

    @Basic
    @Column(name = "message_location_ip")
    public Object getMessageLocationIp() {
        return messageLocationIp;
    }

    public void setMessageLocationIp(Object messageLocationIp) {
        this.messageLocationIp = messageLocationIp;
    }

    @Basic
    @Column(name = "message_content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "message_length")
    public Integer getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(Integer messageLength) {
        this.messageLength = messageLength;
    }



}
