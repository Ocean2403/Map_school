package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class Department extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        toolbar = findViewById(R.id.app_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Department.this, MainActivity.class));
            }
        });

        DeptData[] deptData = new DeptData[] {
                new DeptData("Tòa A", R.drawable.ic_home),
                new DeptData("Tòa B", R.drawable.ic_home),
                new DeptData("Tòa C", R.drawable.ic_home),
                new DeptData("Tòa D", R.drawable.ic_home),
                new DeptData("Tòa E", R.drawable.ic_home),
                new DeptData("Tòa F", R.drawable.ic_home),
                new DeptData("Tòa H", R.drawable.ic_home),
                new DeptData("Tòa I", R.drawable.ic_home),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DeptAdapter adapter = new DeptAdapter(Arrays.asList(deptData));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
