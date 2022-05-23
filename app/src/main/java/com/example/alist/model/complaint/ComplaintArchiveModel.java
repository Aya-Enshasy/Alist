
package com.example.alist.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComplaintArchiveModel implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("complaintData")
    @Expose
    private ComplaintData complaintData;
    private final static long serialVersionUID = 5141917390954899217L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ComplaintData getComplaintData() {
        return complaintData;
    }

    public void setComplaintData(ComplaintData complaintData) {
        this.complaintData = complaintData;
    }

}
