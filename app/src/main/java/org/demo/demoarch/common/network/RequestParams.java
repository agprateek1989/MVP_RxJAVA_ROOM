package org.demo.demoarch.common.network;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class RequestParams<T> {

    private boolean isForceUpdate;
    private T body;

    public RequestParams() {
    }

    public boolean isForceUpdate() {
        return isForceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
