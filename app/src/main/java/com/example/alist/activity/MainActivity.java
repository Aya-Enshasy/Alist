package com.example.alist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.alist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                myAsyncTask myAsyncTask=new myAsyncTask();
                myAsyncTask.execute();

    }

    class myAsyncTask extends AsyncTask<String,Integer,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(getApplicationContext(), Complaints.class);
            startActivity(i);
        }

        @Override
        protected Void doInBackground(String... strings) {

            for (int i=0;i<2;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }publishProgress();
            }
            return null;
        }
    }
}