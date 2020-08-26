package com.hibernate.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Post
{
    private long postId;
    private Long postMessageId;
    private Long postForumId;
    private String postLanguage;
    private String postImageFile;

    @Id
    @Column(name = "post_id")
    public long getPostId()
    {
        return postId;
    }

    public void setPostId(final long postId)
    {
        this.postId = postId;
    }

    @Basic
    @Column(name = "post_message_id")
    public Long getPostMessageId()
    {
        return postMessageId;
    }

    public void setPostMessageId(final Long postMessageId)
    {
        this.postMessageId = postMessageId;
    }

    @Basic
    @Column(name = "post_forum_id")
    public Long getPostForumId()
    {
        return postForumId;
    }

    public void setPostForumId(final Long postForumId)
    {
        this.postForumId = postForumId;
    }

    @Basic
    @Column(name = "post_language")
    public String getPostLanguage()
    {
        return postLanguage;
    }

    public void setPostLanguage(final String postLanguage)
    {
        this.postLanguage = postLanguage;
    }

    @Basic
    @Column(name = "post_image_file")
    public String getPostImageFile()
    {
        return postImageFile;
    }

    public void setPostImageFile(final String postImageFile)
    {
        this.postImageFile = postImageFile;
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

        final Post post = (Post) o;

        if (postId != post.postId)
        {
            return false;
        }
        if (postMessageId != null ? !postMessageId.equals(post.postMessageId) : post.postMessageId != null)
        {
            return false;
        }
        if (postForumId != null ? !postForumId.equals(post.postForumId) : post.postForumId != null)
        {
            return false;
        }
        if (postLanguage != null ? !postLanguage.equals(post.postLanguage) : post.postLanguage != null)
        {
            return false;
        }
        if (postImageFile != null ? !postImageFile.equals(post.postImageFile) : post.postImageFile != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (postId ^ (postId >>> 32));
        result = 31 * result + (postMessageId != null ? postMessageId.hashCode() : 0);
        result = 31 * result + (postForumId != null ? postForumId.hashCode() : 0);
        result = 31 * result + (postLanguage != null ? postLanguage.hashCode() : 0);
        result = 31 * result + (postImageFile != null ? postImageFile.hashCode() : 0);
        return result;
    }
}
