
package com.company.HW04_group18;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.HW04_group18.R;

import java.util.ArrayList;

public class RecyclerViewPageCountAdapter extends RecyclerView.Adapter<RecyclerViewPageCountAdapter.RecyclerViewPageCountAdapterHolder> {

    ArrayList<Integer> pageNumsList;
    PostsFragment parentView;

    public RecyclerViewPageCountAdapter(ArrayList<Integer> pageNumsList, PostsFragment parentView) {
        this.pageNumsList = pageNumsList;
        this.parentView = parentView;
    }

    @NonNull
    @Override
    public RecyclerViewPageCountAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pagecount_layout, parent, false);
        RecyclerViewPageCountAdapterHolder recyclerViewPageCountAdapterHolder = new RecyclerViewPageCountAdapterHolder(view);
        return recyclerViewPageCountAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPageCountAdapterHolder holder, int position) {
        int buttonNumber = pageNumsList.get(position);
        holder.pageCount.setText(buttonNumber + "");
        holder.pageCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentView.updatePageNumberAndRefesh(buttonNumber);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageNumsList.size();
    }

    public class RecyclerViewPageCountAdapterHolder extends RecyclerView.ViewHolder {
        Button pageCount;
        public RecyclerViewPageCountAdapterHolder(@NonNull View itemView) {
            super(itemView);
            pageCount = itemView.findViewById(R.id.pagecount_layout_button);
        }
    }
}
