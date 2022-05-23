package com.example.alist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alist.R;

public class Complaints extends AppCompatActivity {
    ConstraintLayout constraintLayout,constraintLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout2 = findViewById(R.id.constraintlayout2);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),SubmitComplaints.class);
                        startActivity(intent);
            }
        });

        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),ComplaintsArchive.class);
                startActivity(intent);
            }
        });
    }
}