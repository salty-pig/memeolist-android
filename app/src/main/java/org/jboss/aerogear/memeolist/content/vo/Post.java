/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.aerogear.memeolist.content.vo;

import android.os.Parcel;
import android.os.Parcelable;

import org.jboss.aerogear.android.core.RecordId;

import java.util.Date;

/**
 * summers
 */

public class Post implements Parcelable {

    @RecordId
    private Long id;

    protected Post(Parcel in) {
        fileUrl = in.readString();
        comment = in.readString();
        posted = new Date(in.readLong());
        owner = new RedHatUser();
        owner.setDisplayName(in.readString());
        owner.setId(in.readLong());
        owner.setUsername(in.readString());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    private RedHatUser owner;

    private String fileUrl;
    private String comment;

    private Date posted;

    public RedHatUser getOwner() {
        return owner;
    }

    public void setOwner(RedHatUser owner) {
        this.owner = owner;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileUrl);
        dest.writeString(comment);
        dest.writeLong(posted.getTime());
        dest.writeString(owner.getDisplayName());
        dest.writeLong(owner.getId());
        dest.writeString(owner.getUsername());

    }
}
