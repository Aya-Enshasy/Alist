package com.example.alist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MassageModel implements Serializable
{

    @SerializedName("massage")
    @Expose
    private String massage;

    @SerializedName("data")
    @Expose
List<Data> list=null;
    private final static long serialVersionUID = -6094150931949342326L;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }
}