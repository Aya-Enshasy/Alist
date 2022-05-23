package com.example.alist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alist.R;
import com.example.alist.adapter.ArchiveAdapter;
import com.example.alist.adapter.ChatAdapter;
import com.example.alist.databinding.ActivityComplaintDetailsBinding;
import com.example.alist.interfaces.Service;
import com.example.alist.model.Data;
import com.example.alist.model.MassageModel;
import com.example.alist.model.complaint.ComplaintData;
import com.example.alist.model.filter.FilterData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintDetails extends AppCompatActivity {
    int Id ;
    Service service;
    int ArchiveId ;
    String massage;
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    List<Data> list;
    ActivityComplaintDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // getAllMassages();
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>() ;

        list.add(new Data("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص ...عرض اكثر","user"));
        list.add(new Data("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص ...عرض اكثر","company"));
        list.add(new Data("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص ...عرض اكثر","user"));
        list.add(new Data("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص ...عرض اكثر","user"));

        Log.e("list",list.size()+"");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        chatAdapter = new ChatAdapter(this);
        chatAdapter.setData(list);
        recyclerView.setAdapter(chatAdapter);


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                massage=binding.massage.getText().toString();
              //  sendMassage(massage);
            }
        });

        if (getIntent() != null) {
            ArchiveId = getIntent().getIntExtra(ArchiveAdapter.ARCHIVE_ID, 0);
        }

//        service= Service.ApiClient.getRetrofitInstance();
       // getProductData();
    }
    private void getProductData() {

        service.getDetails(Id).enqueue(new Callback<ComplaintData>() {
            @Override
            public void onResponse(Call<ComplaintData> call, Response<ComplaintData> response) {
                if (response.body() != null){
                    binding.date.setText( response.body().getDate());
                    binding.complaintDetails.setText( response.body().getMassage());
                    binding.title.setText( response.body().getName());
                }
            }
            @Override
            public void onFailure(Call<ComplaintData> call, Throwable t) {
                t.printStackTrace();
            }

        });


    }



    private void sendMassage(String uMessage) {

//        Login.SP = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
//        String token = Login.SP.getString(Login.TokenKey, "");//"No name defined" is the default value.
        RequestBody requestBody = RequestBody.create(MediaType.parse("Multipart/form-data"),massage);
        service.massage("Bearer " + "token",requestBody).enqueue(new Callback<MassageModel>() {
            @Override
            public void onResponse(Call<MassageModel> call, Response<MassageModel> response) {
                Log.e("response code", response.code() + "");

                if (response.body() != null) {

                    Log.e("Message",   "Message");
//                    chatAdapter.setData(response.body().getList());
                } else {
                    Log.e("errorMessage",   "errorMessage");
                }
            }

            @Override
            public void onFailure(Call<MassageModel> call, Throwable t) {
                t.printStackTrace();
            }

        });


    }

    private void getAllMassages() {
//        Login.SP = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
//        String token = Login.SP.getString(Login.TokenKey, "");//"No name defined" is the default value.
        service.allMassages("Bearer " + "token").enqueue(new Callback<MassageModel>() {
            @Override
            public void onResponse(Call<MassageModel> call, Response<MassageModel> response) {
                if (response.body() != null){
                    list = response.body().getList();
                    chatAdapter.setData(list);
                }
            }
            @Override
            public void onFailure(Call<MassageModel> call, Throwable t) {
                t.printStackTrace();
            }

        });


    }

}