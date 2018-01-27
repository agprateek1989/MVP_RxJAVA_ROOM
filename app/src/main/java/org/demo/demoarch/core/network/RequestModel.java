package org.demo.demoarch.core.network;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class RequestModel<R> {

    private boolean isProgress;
    private boolean isSuccess;
    private String errorMessage;


    private R data;



    public void setProgress(boolean progress) {
        isProgress = progress;
    }

    public boolean isProgres(){
        return this.isProgress;
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

    private RequestModel(boolean progress, boolean success, String msg, R data){
        this.isProgress = progress;
        this.isSuccess = success;
        this.errorMessage = msg;
        this.data = data;
    }

    public static RequestModel isProgress(){

        return  new RequestModel(true,false,null,null);
    }

    public static <R>RequestModel<R> isSuccess(R data){

        return  new RequestModel(false,true,null,data);
    }

    public static <R>RequestModel<R> error(String msg){

        return  new RequestModel(false,false,msg,null);
    }




}
