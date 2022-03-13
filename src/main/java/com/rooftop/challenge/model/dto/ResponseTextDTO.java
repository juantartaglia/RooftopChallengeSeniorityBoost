package com.rooftop.challenge.model.dto;

import java.util.HashMap;
import java.util.Map;

public class ResponseTextDTO {
    private Long id;

    private String hash;

    private int chars;

    private Map<String,Integer> result=new HashMap<String,Integer>();


    public ResponseTextDTO(Long id, String hash, int chars, Map<String,Integer> result) {
        this.id = id;
        this.hash = hash;
        this.chars = chars;
        this.result = result;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getChars() {
        return this.chars;
    }

    public void setChars(int chars) {
        this.chars = chars;
    }

    public Map<String,Integer> getResult() {
        return this.result;
    }

    public void setResult(Map<String,Integer> result) {
        this.result = result;
    }
    
}
