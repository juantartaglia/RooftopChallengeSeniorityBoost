package com.rooftop.challenge.errorhandler;

public class CustomException extends RuntimeException {
    
    private String message;
    private boolean error = false;
    private int code;


    public CustomException(String message, boolean error, int code) {
        super(message);
        this.message = message;
        this.error = error;
        this.code = code;
    }


    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getError() {
        return this.error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
