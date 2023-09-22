package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Toolbar;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import org.andresoviedo.util.android.AssetUtils;
import org.andresoviedo.util.android.ContentUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public MaterialCardView card_3D, card_list, card_search, card_direction;

    public Toolbar toolbar;

    private Map<String, Object> loadModelParameters = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_3D = findViewById(R.id.card_3D);
        card_list = findViewById(R.id.card_list);
        card_search = findViewById(R.id.card_search);
        card_direction = findViewById(R.id.card_direction);

        card_3D.setOnClickListener(this);
        card_list.setOnClickListener(this);
        card_search.setOnClickListener(this);
        card_direction.setOnClickListener(this);

        toolbar = findViewById(R.id.app_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, User.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.card_3D) {
            loadModelFromAssets();
        }
        if (view.getId() == R.id.card_list) {
            startActivity(new Intent(MainActivity.this, Department.class));
        }
        if (view.getId() == R.id.card_search) {
            startActivity(new Intent(MainActivity.this, Search.class));
        }
        if (view.getId() == R.id.card_direction) {
            startActivity(new Intent(MainActivity.this, WayFinding.class));
        }
    }

    private void loadModelFromAssets() {
        AssetUtils.createChooserDialog(this, "Mô hình", null, "models", "(?i).*\\.(obj|stl|dae|gltf)",
                (String file) -> {
                    if (file != null) {
                        ContentUtils.provideAssets(this);
                        launchModelRendererActivity(Uri.parse("assets://" + getPackageName() + "/" + file));
                    }
                });
    }

    private void launchModelRendererActivity(Uri uri) {
        Log.i("Menu", "Launching renderer for '" + uri + "'");
        Intent intent = new Intent(getApplicationContext(), ModelActivity.class);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("immersiveMode", "true");

        // content provider case
        if (!loadModelParameters.isEmpty()) {
            intent.putExtra("type", loadModelParameters.get("type").toString());
            loadModelParameters.clear();
        }
        startActivity(intent);
    }
}