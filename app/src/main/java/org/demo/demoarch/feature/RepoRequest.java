package org.demo.demoarch.feature;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class RepoRequest {

    private final int page;
    private final int count;

    public RepoRequest(int page, int count) {
        this.page = page;
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }
}
