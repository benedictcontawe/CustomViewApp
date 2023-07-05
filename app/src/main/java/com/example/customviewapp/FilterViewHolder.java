package com.example.customviewapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilterViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = FilterViewHolder.class.getSimpleName();

    private TextView titleTextView, typeTextView;

    public FilterViewHolder(@NonNull View itemView) {
        super(itemView);

        this.titleTextView = itemView.findViewById(R.id.title_text_view);
        this.typeTextView = itemView.findViewById(R.id.type_text_view);
    }

    public void bindDataToViewHolder(FilterModel model, int position) {
        titleTextView.setText(model.getTitle());
        typeTextView.setText(model.getType());
    }
}
