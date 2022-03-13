package com.rooftop.challenge.model.dto;

import javax.validation.constraints.NotEmpty;

public class RequestDTO {
    
    @NotEmpty(message= "text should not be epmty")
    private String text;
    private Integer chars;


    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getChars() {
        return this.chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }

}
