package com.rooftop.challenge.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="response")
@Entity
public class Text implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String hash;

    private int chars;

    @ElementCollection
    private Map<String,Integer> result=new HashMap<String,Integer>();
    
    private boolean deleted = false;
    

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

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
