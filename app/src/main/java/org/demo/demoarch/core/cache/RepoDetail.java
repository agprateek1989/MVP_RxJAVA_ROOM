package org.demo.demoarch.core.cache;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Entity(tableName = "Repo")
public final class RepoDetail {

    @PrimaryKey

    @ColumnInfo(name = "entryid")
    private long mId;

    @ColumnInfo(name = "fullname")
    @Nonnull
    private String fullName;

    @Nonnull
    @ColumnInfo(name = "title")
    private String mTitle;

    @Nonnull
    @ColumnInfo(name = "description")
    private String mDescription;

    @Nonnull
    @ColumnInfo(name = "repourl")
    private String repoUrl;


    @Nonnull
    @ColumnInfo(name = "ownerurl")
    private String mOwnerUrl;

    @ColumnInfo(name = "ownerlogin")
    private String mOwnerLogin;

    @ColumnInfo(name = "forkcount")
    private long forkCount;

    @ColumnInfo(name = "ownerAvatar")
    private String ownerAvatar;


   public RepoDetail(){}

    public String getOwnerAvatar() {
        return ownerAvatar;
    }

    public void setOwnerAvatar(String ownerAvatar) {
        this.ownerAvatar = ownerAvatar;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setTitle(@Nonnull String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDescription(@Nonnull String mDescription) {
        this.mDescription = mDescription;
    }

    public void setRepoUrl(@Nonnull String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public void setOwnerURL(@Nonnull String mOwnerURL) {
        this.mOwnerUrl = mOwnerURL;
    }

    public void setOwnerLogin(String mOwnerLogin) {
        this.mOwnerLogin = mOwnerLogin;
    }



    @NonNull
    public long getId() {
        return mId;
    }

    @Nonnull
    public String getTitle() {
        return mTitle;
    }

    @Nonnull
    public String getDescription() {
        return mDescription;
    }

    @Nonnull
    public String getRepoUrl() {
        return repoUrl;
    }



    @Nonnull
    public String getOwnerUrl() {
        return mOwnerUrl;
    }


    public void setOwnerUrl(@Nonnull String mOwnerUrl) {
        this.mOwnerUrl = mOwnerUrl;
    }

    @Nonnull
    public String getOwnerLogin() {
        return this.mOwnerLogin;
    }

    @Nonnull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(@Nonnull String fullName) {
        this.fullName = fullName;
    }

    public long getForkCount() {
        return forkCount;
    }

    public void setForkCount(long forkCount) {
        this.forkCount = forkCount;
    }
}
