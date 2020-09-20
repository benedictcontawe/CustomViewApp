package com.example.customviewapp;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

abstract public class BaseActionSheetViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = BaseActionSheetViewHolder.class.getSimpleName();
    /**Main */
    private Context context;

    public BaseActionSheetViewHolder(Context context, View view) {
        super(view);
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    abstract void bindDataToViewHolder(final ActionSheetViewHolderModel item,final int position);
}
