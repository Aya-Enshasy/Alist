
package com.example.alist.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComplaintData implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reply_status")
    @Expose
    private String reply_status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("massage")
    @Expose
    private String massage;

    public ComplaintData(Integer id, String name, String reply_status, String date, String massage) {
        this.id = id;
        this.name = name;
        this.reply_status = reply_status;
        this.date = date;
        this.massage = massage;
    }

    private final static long serialVersionUID = 4396347571200688470L;
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReply_status() {
        return reply_status;
    }

    public void setReply_status(String reply_status) {
        this.reply_status = reply_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
