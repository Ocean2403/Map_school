package com.example.test2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeptAdapter extends RecyclerView.Adapter<DeptViewHolder> {
    private List<DeptData> list;

    public DeptAdapter(List<DeptData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DeptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dept_building, parent, false);
        DeptViewHolder holder = new DeptViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeptViewHolder holder, int position) {
        final DeptData deptData = list.get(position);
        holder.deptName.setText(list.get(position).getDeptName());
        holder.deptImage.setImageResource(list.get(position).getDeptImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.relativeLayout.getContext(), DeptDetail.class);
                intent.putExtra("deptName", deptData.getDeptName());
                holder.relativeLayout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
