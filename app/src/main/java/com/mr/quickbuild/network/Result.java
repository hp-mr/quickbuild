package com.mr.quickbuild.network;

/**
 * Created by Garrett on 2018/12/6.
 * contact me krouky@outlook.com
 */
public class Result<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public boolean isOk() {
        return 0==getCode();
    }
}
