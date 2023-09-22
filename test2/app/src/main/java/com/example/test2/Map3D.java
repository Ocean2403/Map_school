package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Map3D extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map3d);
    }

    public void campus_3D(View view) {
        startActivity(new Intent(Map3D.this, ModelActivity.class));
    }

    public void buildingA_3D(View view) {
    }

    public void buildingB_3D(View view) {
    }

    public void buildingC_3D(View view) {
    }

    public void buildingD_3D(View view) {
    }

    public void buildingE_3D(View view) {
    }

    public void buildingF_3D(View view) {
    }

    public void buildingH_3D(View view) {
    }

    public void buildingI_3D(View view) {
    }
}