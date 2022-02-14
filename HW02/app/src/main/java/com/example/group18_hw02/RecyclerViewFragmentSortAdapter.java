

package com.example.group18_hw02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragmentSortAdapter extends RecyclerView.Adapter<RecyclerViewFragmentSortAdapter.SortViewHolder> {

    ArrayList<String> attributes = new ArrayList<String>();
    SortFragment.iListener mListener;

    public  RecyclerViewFragmentSortAdapter (SortFragment.iListener mListener) {
        attributes.add("Age");
        attributes.add("Name");
        attributes.add("State");
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_viewlist, parent, false);
        SortViewHolder sortViewHolder = new SortViewHolder(view);
        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        String attributeAtPosition = attributes.get(position);
        holder.sortCategory.setText(attributeAtPosition);
        holder.ascending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.fetchSortingAttributesFromRecyclerViewFragmentSortAdapter(attributeAtPosition, true);
            }
        });
        holder.descending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.fetchSortingAttributesFromRecyclerViewFragmentSortAdapter(attributeAtPosition, false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class SortViewHolder extends RecyclerView.ViewHolder{
        TextView sortCategory;
        ImageButton ascending, descending;
        public SortViewHolder(@NonNull View itemView) {
            super(itemView);
            sortCategory = itemView.findViewById(R.id.textView_SortCategory);
            ascending = itemView.findViewById(R.id.imageButton_Acending);
            descending = itemView.findViewById(R.id.imageButton_Descending);
        }
    }

}
