package com.example.alist.activity;

import androidx.annotation.Nullable;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.alist.R;
import com.example.alist.interfaces.Service;
import com.example.alist.model.complaint.ComplaintData;
import com.example.alist.model.filter.FilterData;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheet extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    String Input_Date,Input_Date2,Input_Status ;
    List<ComplaintData>list;
    EditText date,date2;
    Service service;
    DatePickerDialog datePickerDialog;
    Button deletefillter,fillterall,wait,close,answer;
    ImageView image_date,image_date2;
    String id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_bottom_sheet, container, false);



        deletefillter = v.findViewById(R.id.delete_filter);
        fillterall = v.findViewById(R.id.filter_all);
        image_date = v.findViewById(R.id.image_date);
        date = v.findViewById(R.id.date);
        date2 = v.findViewById(R.id.date2);
        wait = v.findViewById(R.id.wait);
        answer = v.findViewById(R.id.answer);
        close = v.findViewById(R.id.close);
        image_date2 = v.findViewById(R.id.image_date2);
        list =new ArrayList<>();


        wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id ="0";
                wait.setBackgroundResource(R.drawable.waitbtn);
                answer.setBackgroundResource(R.drawable.searchbtn);
                close.setBackgroundResource(R.drawable.searchbtn);

            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id ="2";
                answer.setBackgroundResource(R.drawable.waitbtn);
                wait.setBackgroundResource(R.drawable.searchbtn);
                close.setBackgroundResource(R.drawable.searchbtn);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id ="2";
                close.setBackgroundResource(R.drawable.waitbtn);;
                answer.setBackgroundResource(R.drawable.searchbtn);
                wait.setBackgroundResource(R.drawable.searchbtn);
            }
        });
        image_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        image_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date2.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
//        service= Service.ApiClient.getRetrofitInstance();

        fillterall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        deletefillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //getData();

        return v;
    }

    private void getData() {
        FilterData user = new FilterData();
        user.setDate2(Input_Date2);
        user.setDate(Input_Date);
        user.setStatus(id);
        service.filter(user).enqueue(new Callback<FilterData>() {
            @Override
            public void onResponse(Call<FilterData> call, Response<FilterData> response) {
                if (response.isSuccessful()){

                    Log.e("Success", new Gson().toJson(response.body()));

                } else {
                    String errorMessage = parseError(response);
                    Log.e("errorMessage", errorMessage + "");
                }
            }

            @Override
            public void onFailure(Call<FilterData> call, Throwable t) {

            }
            });
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
    public static String parseError(Response<?> response) {
        String errorMsg = null;
        try {
            JSONObject jsonObject = new JSONObject(response.errorBody().string());
            JSONObject jsonObject2 = jsonObject.getJSONObject("errors");
            JSONArray jsonArray = jsonObject2.getJSONArray("password");
            String s = jsonArray.getString(0);


            return s;
        } catch (Exception e) {
        }
        return errorMsg;
    }
}

