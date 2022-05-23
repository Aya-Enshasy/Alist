package com.example.alist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.alist.R;
import com.example.alist.adapter.ArchiveAdapter;
import com.example.alist.databinding.ActivityComplaintsArchiveBinding;
import com.example.alist.interfaces.Service;
import com.example.alist.model.complaint.ComplaintData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintsArchive extends AppCompatActivity implements BottomSheet.BottomSheetListener{
    ActivityComplaintsArchiveBinding binding;
    RecyclerView recyclerView;
    ArchiveAdapter archiveAdapter;
    List<ComplaintData> list;
    Service service;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintsArchiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);
        list = new ArrayList<>() ;

        list.add(new ComplaintData(1,"عنوان الشكوى","0","22/05/2022",""));
        list.add(new ComplaintData(1,"عنوان الشكوى","1","22/05/2022",""));
        list.add(new ComplaintData(1,"عنوان الشكوى","3","22/05/2022",""));

        Log.e("list",list.size()+"");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        archiveAdapter = new ArchiveAdapter(this);
        archiveAdapter.setData(list);
        recyclerView.setAdapter(archiveAdapter);

     //   service = Service.ApiClient.getRetrofitInstance();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Complaints.class);
                startActivity(intent);

            }
        });
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                final Call<List<ComplaintData>> call = service.searchSpacecraft(query);
                call.enqueue(new Callback<List<ComplaintData>>() {

                    @Override
                    public void onResponse(Call<List<ComplaintData>> call, Response<List<ComplaintData>> response) {
                        if (response.body() != null) {
                            list = response.body();
                            archiveAdapter.setData(list);

                        }
                    }
                    @Override
                    public void onFailure(Call<List<ComplaintData>> call, Throwable throwable) {
//                        list(new ArrayList<ComplaintData>());
                        Toast.makeText(getApplicationContext(), "ERROR: "+throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });


       // getArchive();
    }

    private void getArchive() {

        service.getArchive().enqueue(new Callback<List<ComplaintData>>() {
            @Override
            public void onResponse(Call<List<ComplaintData>> call, Response<List<ComplaintData>> response) {

                if (response.body() != null) {
                    list = response.body();
                    archiveAdapter.setData(list);

                }

            }
            @Override
            public void onFailure(Call<List<ComplaintData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.e("error",t.getMessage());
            }
        });
    }
    @Override
    public void onButtonClicked(String text) {

    }


}