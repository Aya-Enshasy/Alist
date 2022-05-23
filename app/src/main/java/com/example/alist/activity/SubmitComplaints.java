package com.example.alist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.alist.R;
import com.example.alist.databinding.ActivitySubmitComplaintsBinding;
import com.example.alist.interfaces.Service;
import com.example.alist.model.ComplaintModel;
import com.example.alist.model.RequestModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitComplaints extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String PREF_NAME = "Preferences";
    ActivitySubmitComplaintsBinding binding;
    String Input_NearPlace, Input_City, Input_Address, Input_Mobile, Input_Phone,
            Input_Email, Input_Username, Input_UserNumber, Input_Details, Input_ComplaintsTitle;

    public static final String NearPlaceKey = "NearPlace_K", CityKey = "City_K",
            AddressKey = "Address_K", MobileKey = "Mobile_K",PhoneKey = "Phone_K", EmailKey = "Email_K"
            , UsernameKey = "Username_K",UserNumberKey = "UserNumber_K", DetailsKey = "Details_K",
            ComplaintsTitleKey = "ComplaintsTitle_K";

    String[] list = {"اختر","مقترح", "شكوى", "اشارة صيانة"};
    String[] list1 = {"اختر","الادارة العامة", "غزة", "الشمال", "الوسطى", "خانيونيس", "رفح"};

    public static SharedPreferences SP;
    public static SharedPreferences.Editor EDIT;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubmitComplaintsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Complaints.class);
                startActivity(intent);

            }
        });


        binding.spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(ad);


        binding.spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list1);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(ad2);


        SP = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        EDIT = SP.edit();

        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvComplaintsTitle.setText("");
                binding.tvTitle.setText("");
                binding.tvCity.setText("");
                binding.tvNearPlace.setText("");
                binding.tvMobile.setText("");
                binding.tvPhone.setText("");
                binding.tvEmail.setText("");
                binding.tvUserName.setText("");
                binding.tvUserNumber.setText("");
                binding.details.setText("");
//                binding.spinner.setPrompt("");

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Input_Address = binding.tvTitle.getText().toString();
                Input_City = binding.tvCity.getText().toString();
                Input_NearPlace = binding.tvNearPlace.getText().toString();
                Input_Mobile = binding.tvMobile.getText().toString();
                Input_Phone = binding.tvPhone.getText().toString();
                Input_Email = binding.tvEmail.getText().toString();
                Input_Username = binding.tvUserName.getText().toString();
                Input_UserNumber = binding.tvUserNumber.getText().toString();
                Input_Details = binding.details.getText().toString();
                Input_ComplaintsTitle = binding.tvComplaintsTitle.getText().toString();

                EDIT.putString(AddressKey, Input_Address);
                EDIT.putString(NearPlaceKey, Input_NearPlace);
                EDIT.putString(CityKey, Input_City);
                EDIT.putString(MobileKey, Input_Mobile);
                EDIT.putString(PhoneKey, Input_Phone);
                EDIT.putString(EmailKey, Input_Email);
                EDIT.putString(UsernameKey, Input_Username);
                EDIT.putString(UserNumberKey, Input_UserNumber);
                EDIT.putString(DetailsKey, Input_Details);
                EDIT.putString(ComplaintsTitleKey, Input_ComplaintsTitle);
                EDIT.apply();

                if (!Input_Address.isEmpty() && !Input_NearPlace.isEmpty()
                        && !Input_City.isEmpty() && !Input_Mobile.isEmpty()&& !Input_Phone.isEmpty()
                        && !Input_Email.isEmpty() && !Input_Username.isEmpty()&& !Input_UserNumber.isEmpty()
                        && !Input_Details.isEmpty()&& !Input_ComplaintsTitle.isEmpty()) {
                    Input_Address = binding.tvTitle.getText().toString();
                    Input_Address = binding.tvTitle.getText().toString();

                    Input_Details = binding.details.getText().toString();
                    //api
                    RequestModel user = new RequestModel();
                    user.setAddress(Input_Address);

                    user.setCity(Input_City);
                    user.setComplaintsTitle(Input_ComplaintsTitle);
                    user.setDetails(Input_Details);
                    user.setEmail(Input_Email);
                    user.setMobile(Input_Mobile);
                    user.setNearPlace(Input_NearPlace);
                    user.setPhone(Input_Phone);
                    user.setUsername(Input_Username);
                    user.setUserNumber(Input_UserNumber);
                    Service.ApiClient.getRetrofitInstance().userData("application/json", user).enqueue(new Callback<ComplaintModel>() {
                        @Override
                        public void onResponse(Call<ComplaintModel> call, retrofit2.Response<ComplaintModel> response) {
                            Log.e("response code", response.code() + "");
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getBaseContext(), ComplaintsArchive.class);
                                startActivity(intent);
                                Toast.makeText(getBaseContext(), "Welcome" , Toast.LENGTH_LONG).show();
                                Log.e("Success", new Gson().toJson(response.body()));
                            } else {
                                String errorMessage = parseError(response);
//                                UtilsMethods.createFailSnackbar(binding.parent, errorMessage).show();
                                Log.e("errorMessage", errorMessage + "");
                            }


                        }

                        @Override
                        public void onFailure(Call<ComplaintModel> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });

                } else {
                    Toast.makeText(getBaseContext(), "Empty", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public static String parseError(Response<?> response) {
        String errorMsg = null;
        try {
            JSONObject jsonObject = new JSONObject(response.errorBody().string());
            JSONObject jsonObject2 = jsonObject.getJSONObject("errors");
            JSONArray jsonArray = jsonObject2.getJSONArray("password");
            String s = jsonArray.getString(0);

//            errorMsg = jObjError.getString("message");
//            Util.Logd(jObjError.getString("errorMessage"));
            return s;
        } catch (Exception e) {
//            Util.Logd(e.getMessage());
        }
        return errorMsg;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // which is selected in spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}