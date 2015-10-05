package org.jboss.aerogear.memeolist.content.vo;

import org.jboss.aerogear.android.core.RecordId;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by summers on 10/1/15.
 */
public class PostComment implements Serializable {

    @RecordId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date postedDate;

    private String comment;

    private Long postId;

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
