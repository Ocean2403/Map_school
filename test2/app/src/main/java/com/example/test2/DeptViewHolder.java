package com.example.test2;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DeptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView deptName;
    protected ImageView deptImage;
    protected RelativeLayout relativeLayout;

    public DeptViewHolder(@NonNull View itemView) {
        super(itemView);
        this.deptName = (TextView) itemView.findViewById(R.id.deptName);
        this.deptImage = (ImageView) itemView.findViewById(R.id.deptImage);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.list_item);
    }

    @Override
    public void onClick(View v) {
    }
}
