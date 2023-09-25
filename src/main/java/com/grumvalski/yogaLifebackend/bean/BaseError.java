package com.grumvalski.yogaLifebackend.bean;

public class BaseError {

    private int statusCode;
    private String title;

    public BaseError() {
    }

    public BaseError(int statusCode, String title) {
        this.statusCode = statusCode;
        this.title = title;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
