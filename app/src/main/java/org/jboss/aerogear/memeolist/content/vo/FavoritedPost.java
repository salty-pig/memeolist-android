package org.jboss.aerogear.memeolist.content.vo;

import org.jboss.aerogear.android.core.RecordId;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by summers on 10/1/15.
 */
public class FavoritedPost implements Serializable {

    @RecordId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long postId;

    private Date favoritedDate;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Date getFavoritedDate() {
        return favoritedDate;
    }

    public void setFavoritedDate(Date favoritedDate) {
        this.favoritedDate = favoritedDate;
    }
}
