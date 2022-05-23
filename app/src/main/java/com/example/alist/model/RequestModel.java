
package com.example.alist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//import javax.annotation.Generated;

public class RequestModel implements Serializable
{
    @SerializedName("NearPlace")
    @Expose
    private String NearPlace;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("Phone")
    @Expose
    private String Phone;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("ComplaintsTitle")
    @Expose
    private String ComplaintsTitle;
    @SerializedName("UserNumber")
    @Expose
    private String UserNumber;
    @SerializedName("Details")
    @Expose
    private String Details;
    private final static long serialVersionUID = -6094150931949342326L;

    public String getNearPlace() {
        return NearPlace;
    }

    public void setNearPlace(String nearPlace) {
        NearPlace = nearPlace;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComplaintsTitle() {
        return ComplaintsTitle;
    }

    public void setComplaintsTitle(String complaintsTitle) {
        ComplaintsTitle = complaintsTitle;
    }

    public String getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(String userNumber) {
        UserNumber = userNumber;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}