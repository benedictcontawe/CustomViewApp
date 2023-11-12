package com.example.customappactionsheet;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class ActionSheetViewHolder extends BaseActionSheetViewHolder {

    private static final String TAG = ActionSheetViewHolder.class.getSimpleName();
    private ActionSheetListener listener;
    private CardView cardView;
    private TextView textView;

    public ActionSheetViewHolder(Context context, ActionSheetListener listener, View view) {
        super(context, view);
        this.listener = listener;
        cardView = (CardView)view.findViewById(R.id.card);
        textView = (TextView)view.findViewById(R.id.text);
    }

    @Override
    void bindDataToViewHolder(final ActionSheetViewHolderModel item, final int position) {
        textView.setText(item.getData());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItem(item, position);
            }
        });
    }
}