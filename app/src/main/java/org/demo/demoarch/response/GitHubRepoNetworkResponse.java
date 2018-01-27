package org.demo.demoarch.response;

import java.util.List;

/**
 * Created by pagga9 on 1/25/2018.
 */

public class GitHubRepoNetworkResponse {

    private List<GitHubRepo> list ;

    public List<GitHubRepo> getList() {
        return list;
    }

    public void setList(List<GitHubRepo> list) {
        this.list = list;
    }
}
