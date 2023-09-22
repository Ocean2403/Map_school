package com.example.test2;

import java.util.ArrayList;

public class DeptFloor {
    private String floor;
    private ArrayList<DeptRoom> list = new ArrayList<DeptRoom>();

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public ArrayList<DeptRoom> getList() {
        return list;
    }

    public void setList(ArrayList<DeptRoom> list) {
        this.list = list;
    }
}
