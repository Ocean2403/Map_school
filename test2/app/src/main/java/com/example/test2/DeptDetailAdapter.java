package com.example.test2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeptDetailAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<DeptFloor> deptList;

    public DeptDetailAdapter(Context context, ArrayList<DeptFloor> list) {
        this.mContext = context;
        this.deptList = list;
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return deptList.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return deptList.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.dept_floor, null);
        }
        DeptFloor dept = (DeptFloor) getGroup(groupPosition);
        ((TextView) convertView.findViewById(R.id.dept_floor)).setText(dept.getFloor());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.dept_room, null);
        }
        DeptRoom deptRoom = (DeptRoom) getChild(groupPosition, childPosition);
        ((TextView) convertView.findViewById(R.id.dept_room)).setText(deptRoom.getRoom());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
