package com.example.customappactionsheet;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActionSheetAdapter extends RecyclerView.Adapter<BaseActionSheetViewHolder> {

    private static final String TAG = ActionSheetAdapter.class.getSimpleName();
    private static ActionSheetListener listener;
    private static final int TopView = 0;
    private static final int CenterView = 1;
    private static final int BottomView = 2;
    private static final int DefaultView = 4;
    private static List<ActionSheetViewHolderModel> list = new ArrayList<ActionSheetViewHolderModel>();

    public ActionSheetAdapter(ActionSheetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseActionSheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TopView:
                Log.d(TAG,"TopView");
                view = layoutInflater.inflate(R.layout.action_sheet_top_cell, parent, false);
                return new ActionSheetTopViewHolder(parent.getContext(), listener, view);
            case CenterView:
                Log.d(TAG,"CenterView");
                view = layoutInflater.inflate(R.layout.action_sheet_center_cell, parent, false);
                return new ActionSheetCenterViewHolder(parent.getContext(), listener, view);
            case BottomView:
                Log.d(TAG,"BottomView");
                view = layoutInflater.inflate(R.layout.action_sheet_bottom_cell, parent, false);
                return new ActionSheetBottomViewHolder(parent.getContext(), listener, view);
            default:
                Log.d(TAG,"default");
                view = layoutInflater.inflate(R.layout.action_sheet_cell, parent, false);
                return new ActionSheetViewHolder(parent.getContext(), listener, view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseActionSheetViewHolder holder, int position) {
        holder.bindDataToViewHolder(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() == 1 && position == 0) {
            return DefaultView;
        } else if (list.size() > 1 && position == 0) {
            return TopView;
        } else if (list.size() > 1 && position + 1 == list.size()) {
            return BottomView;
        } else {
            return CenterView;
        }
    }

    public void setItems(List<ActionSheetViewHolderModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}