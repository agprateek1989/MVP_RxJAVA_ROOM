package org.demo.demoarch.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pagga9 on 1/25/2018.
 */

public class GitHubRepo {

    private long id;
    private String name;
    private OwnerResponse owner;
    @SerializedName("html_url")
    private String url;
    @SerializedName("description")
    private String desc;
    @SerializedName("forks_count")
    private int forkCount;

    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(OwnerResponse owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }
}
