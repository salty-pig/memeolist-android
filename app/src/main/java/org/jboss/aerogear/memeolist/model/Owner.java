package org.jboss.aerogear.memeolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jboss.aerogear.android.core.RecordId;

/**
 * Created by summers on 6/18/15.
 */
public class Owner implements Parcelable {
    protected Owner(Parcel in) {
        this.id = in.readLong();
        this.username = in.readString();
        this.displayName = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeString(displayName);
    }

    @RecordId
    private Long id;
    private String username;
    private String displayName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
