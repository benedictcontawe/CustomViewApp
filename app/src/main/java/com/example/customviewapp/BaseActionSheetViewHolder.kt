package com.example.customviewapp

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseActionSheetViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG : String = BaseActionSheetViewHolder::class.java.simpleName
    }
    /**Main  */
    private val context : Context

    constructor(context : Context, view : View) : super(view) {
        this.context = context
    }

    public fun getContext() : Context {
        return context
    }

    abstract fun bindDataToViewHolder(item: ActionSheetViewHolderModel, position: Int);
}