package com.example.customappactionsheet

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActionSheetAdapter : RecyclerView.Adapter<BaseActionSheetViewHolder> {

    companion object {
        private val TAG : String = ActionSheetAdapter::class.java.simpleName
        private lateinit var listener : ActionSheetListener
        private const val TopView : Int = 0
        private const val CenterView : Int = 1
        private const val BottomView : Int = 2
        private const val DefaultView : Int = 4
        private val list : MutableList<ActionSheetViewHolderModel> = mutableListOf<ActionSheetViewHolderModel>()
    }

    constructor(listener: ActionSheetListener) {
        ActionSheetAdapter.listener = listener
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : BaseActionSheetViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View
        return when(viewType) {
            TopView -> { Log.d(TAG,"TopView ActionSheetTopViewHolder")
                view = layoutInflater.inflate(R.layout.action_sheet_top_cell, parent, false)
                return ActionSheetTopViewHolder(listener, view)
            }
            CenterView -> { Log.d(TAG,"CenterView ActionSheetCenterViewHolder")
                view = layoutInflater.inflate(R.layout.action_sheet_center_cell, parent, false)
                return ActionSheetCenterViewHolder(listener, view)
            }
            BottomView -> { Log.d(TAG,"BottomView ActionSheetBottomViewHolder")
                view = layoutInflater.inflate(R.layout.action_sheet_bottom_cell, parent, false)
                return ActionSheetBottomViewHolder(listener, view)
            }
            else -> { Log.d(TAG,"else ActionSheetViewHolder")
                view = layoutInflater.inflate(R.layout.action_sheet_cell, parent, false)
                return ActionSheetViewHolder(listener, view)
            }
        }
    }

    override fun onBindViewHolder(holder : BaseActionSheetViewHolder, position : Int) {
        holder.bindDataToViewHolder(list.get(position), position)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    override fun getItemViewType(position : Int) : Int {
        if (list.size == 1 && position == 0) {
            return DefaultView
        } else if (list.size > 1 && position == 0) {
            return TopView
        } else if (list.size > 1 && position + 1 == list.size) {
            return BottomView
        } else {
            return CenterView
        }
    }

    public fun setItems(items : List<ActionSheetViewHolderModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}