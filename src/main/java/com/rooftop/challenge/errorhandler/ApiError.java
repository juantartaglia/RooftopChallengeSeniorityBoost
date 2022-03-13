package com.rooftop.challenge.errorhandler;

public class ApiError {
    
    private Boolean error;
    private String message;
    private int code;

    public ApiError(Boolean error, String message, int code) {
        this.message = message;
        this.error = error;
        this.code = code;
    }

    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
