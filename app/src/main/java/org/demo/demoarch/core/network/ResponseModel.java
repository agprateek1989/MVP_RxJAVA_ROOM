package org.demo.demoarch.core.network;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class ResponseModel<R> {

    private boolean isCached;
    private boolean isSuccess;
    private String errorMessage;
    private R data;

    public void setCached(boolean cached) {
        isCached = cached;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    private ResponseModel(boolean isCached, boolean success, String msg, R data){
        this.isCached = isCached;
        this.isSuccess = success;
        this.errorMessage = msg;
        this.data = data;
    }

    public static <R>ResponseModel<R> isCached(R data){

        return  new ResponseModel(true,false,null,data);
    }

    public static <R>ResponseModel<R> isSuccess(R data){

        return  new ResponseModel(false,true,null,data);
    }

    public static <R>ResponseModel<R> error(String msg){

        return  new ResponseModel(false,false,msg,null);
    }
}
