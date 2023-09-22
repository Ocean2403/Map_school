package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.List;

public class WayFinding extends AppCompatActivity {

        private List<SearchData> countryList;
        private FragmentManager fragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wayfinding);
//        fillCountryList();
//        AutoCompleteTextView editText = findViewById(R.id.actv);
//        AutoCompleteCountryAdapter adapter = new AutoCompleteCountryAdapter(this,countryList);
//        editText.setAdapter(adapter);
//
//        editText.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    // Perform action on key press
//                   // Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
//                    for(CountryItem item : countryListFull){
//                        if(editText.getText()==)
//                            return true;
//                    }
//                }
//                return false;
//            }
//        });


            fragment = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragment.beginTransaction();
            fragmentTransaction.replace(R.id.container, new WayFindingFragment());//add và replace khác nhau là nếu có fragment 1 add thêm fragment 2 thì fragment 1 vẫn chạy ,còn khi dùng replace thì khi xuất hiện fragment 2 thì fragment 1 sẽ bị ngừng lại
            fragmentTransaction.commit();
        }
    }