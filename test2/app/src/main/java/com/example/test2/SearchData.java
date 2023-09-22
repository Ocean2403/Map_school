package com.example.test2;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class SearchData {
    private String Search;
    private int hinh;
    private String maphong;
    private String tenphong;
    private String tang;
    private String toa;
    private String toakodau;
    private String link;

    public String getToakodau() {
        return toakodau;
    }

    public void setToakodau(String toakodau) {
        this.toakodau = toakodau;
    }

    public SearchData(String search, int hinh, String maphong, String tenphong, String tang, String toa, String toakodau, String link) {
        Search = search;
        this.hinh = hinh;
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.tang = tang;
        this.toa = toa;
        this.toakodau = toakodau;
        this.link=link;



    }
    public SearchData(String search, int hinh, String maphong, String tenphong, String tang, String toa, String toakodau) {
        Search = search;
        this.hinh = hinh;
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.tang = tang;
        this.toa = toa;
        this.toakodau = toakodau;



    }

    public SearchData(String search, int hinh, String maphong, String tenphong, String tang, String toa) {
        Search = search;
        this.hinh = hinh;
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.tang = tang;
        this.toa = toa;
    }

    public SearchData(String search, String maphong, String tenphong, String tang, String toa) {
        Search = search;
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.tang = tang;
        this.toa = toa;
    }

    public String getSearch() {
        return Search;
    }

    public void setSearch(String search) {
        Search = search;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    public String getToa() {
        return toa;
    }

    public void setToa(String toa) {
        this.toa = toa;
    }
    public String getLink() {
        return link;
    }
}
