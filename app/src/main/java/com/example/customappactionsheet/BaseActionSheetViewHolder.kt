package com.example.customappactionsheet

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseActionSheetViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG : String = BaseActionSheetViewHolder::class.java.simpleName
    }

    constructor(view : View) : super(view) {

    }

    abstract fun bindDataToViewHolder(item: ActionSheetViewHolderModel, position: Int)
}