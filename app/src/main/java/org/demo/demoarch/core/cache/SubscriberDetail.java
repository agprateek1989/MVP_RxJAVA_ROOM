package org.demo.demoarch.core.cache;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Entity(tableName = "subscriber")
public class SubscriberDetail {
    @PrimaryKey
    @ColumnInfo(name = "entryid")
    private  long id;


    @ColumnInfo(name = "repoid")
    private long repoid;

    @Nonnull
    @ColumnInfo(name = "name")
    private  String name;

    @Nonnull
    @ColumnInfo(name = "profileurl")
    private  String profileUrl;

    @Nonnull
    @ColumnInfo(name = "avatar")
    private  String avatar;

    public void setId(long mId) {
        this.id = mId;
    }

    public void setRepoid(long repoid) {
        this.repoid = repoid;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public void setProfileUrl(@Nonnull String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setAvatar(@Nonnull String avatar) {
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    @Nonnull
    public long getRepoSubscriber() {
        return repoid;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getProfileUrl() {
        return profileUrl;
    }

    @Nonnull
    public String getAvatar() {
        return avatar;
    }

    public long getRepoid() {
        return repoid;
    }



}
