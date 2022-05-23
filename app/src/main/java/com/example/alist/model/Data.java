package com.example.alist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable
{

    @SerializedName("massage")
    @Expose
    private String massage;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("sender")
    @Expose
    private String sender;

    public Data(String massage, String sender) {
        this.massage = massage;
        this.sender = sender;
    }

    private final static long serialVersionUID = -6094150931949342326L;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
