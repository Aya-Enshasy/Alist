
package com.example.alist.model.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataModal implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("filterData")
    @Expose
    private FilterData filterData;
    private final static long serialVersionUID = -5286599594202548749L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FilterData getFilterData() {
        return filterData;
    }

    public void setFilterData(FilterData filterData) {
        this.filterData = filterData;
    }

}
