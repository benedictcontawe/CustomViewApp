package com.example.customviewapp

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class ActionSheetBottomViewHolder : BaseActionSheetViewHolder {

    companion object {
        private val TAG : String = ActionSheetBottomViewHolder::class.java.simpleName
    }

    private val listener : ActionSheetListener;
    private val cardView : CardView;
    private val textView : TextView;

    constructor(context : Context, listener : ActionSheetListener, view : View) : super(context, view) {
        this.listener = listener;
        this.cardView = view.findViewById(R.id.card);
        this.textView = view.findViewById(R.id.text);
    }

    override fun bindDataToViewHolder(item : ActionSheetViewHolderModel, position : Int) {
        textView.setText(item.data);
        cardView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                listener.onClickItem(item, position)
            }
        })
    }
}