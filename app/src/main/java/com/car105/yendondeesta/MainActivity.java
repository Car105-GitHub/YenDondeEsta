package com.car105.yendondeesta;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMap(View view) {
        Intent intentMap = new Intent(this,MapsActivity.class);
        startActivity(intentMap);
    }

    public void goToDB(View view) {
        Intent intentDB = new Intent(this,dbActivity.class);
        startActivity(intentDB);
    }
}