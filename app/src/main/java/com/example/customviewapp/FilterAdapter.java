package com.example.customviewapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterViewHolder> {

    private static final String TAG = FilterAdapter.class.getSimpleName();
    private final List<FilterModel> list;

    public FilterAdapter() {
        super();
        list = new ArrayList<FilterModel>();

    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cell_filter, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        holder.bindDataToViewHolder(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<FilterModel> list) {
        Log.d(TAG,"setItems(" + list + ")");
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
