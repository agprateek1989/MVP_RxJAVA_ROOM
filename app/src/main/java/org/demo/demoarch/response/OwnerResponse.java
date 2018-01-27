package org.demo.demoarch.response;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pagga9 on 1/25/2018.
 */

public class OwnerResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;
    @SerializedName("html_url")
    private String url;
    @SerializedName("avatar_url")
    private String avatar;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
