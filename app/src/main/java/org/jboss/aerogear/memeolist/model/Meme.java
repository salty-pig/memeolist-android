package org.jboss.aerogear.memeolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jboss.aerogear.android.core.RecordId;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by summers on 6/18/15.
 */
public class Meme implements Parcelable {
    public Meme(Parcel in) {
        this.id = in.readLong();
        this.posted = new Date(in.readLong());
        try {
            this.fileUrl = new URL(in.readString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.comment = in.readString();
        this.ownerId = in.readLong();
    }

    public static final Creator<Meme> CREATOR = new Creator<Meme>() {
        @Override
        public Meme createFromParcel(Parcel in) {
            return new Meme(in);
        }

        @Override
        public Meme[] newArray(int size) {
            return new Meme[size];
        }
    };

    public Meme() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(posted.getTime());
        dest.writeString(fileUrl.toString());
        dest.writeString(comment);
        dest.writeLong(ownerId);
    }

    @RecordId
    private Long id;

    private Date posted;
    private URL fileUrl;
    private String comment;
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public URL getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(URL fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
