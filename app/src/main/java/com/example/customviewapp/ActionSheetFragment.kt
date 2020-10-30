package com.example.customviewapp

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_action_sheet.*

class ActionSheetFragment : BottomSheetDialogFragment(), View.OnClickListener, ActionSheetListener {

    companion object {
        private val TAG : String = ActionSheetFragment::class.java.simpleName
    }

    private var actionSheetAdapter : ActionSheetAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.ActionSheetTheme);
    }

    override fun onCreateDialog(savedInstanceState : Bundle?) : Dialog {
        val dialog : Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(object : DialogInterface.OnShowListener {
            override fun onShow(dialog: DialogInterface?) {
                val bottomSheetDialog: BottomSheetDialog = dialog as BottomSheetDialog;
                val bottomSheetBehavior: BottomSheetBehavior<*> = bottomSheetDialog.getBehavior();
                val displayMetrics: DisplayMetrics = getResources().getDisplayMetrics();
                val peekHeight: Int = (displayMetrics.heightPixels * .80).toInt();
                bottomSheetBehavior.setPeekHeight(peekHeight);
            }
        })
        return dialog
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_action_sheet, container, false);
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()?.getWindow()?.setDimAmount(1.0f);
        actionSheetAdapter = ActionSheetAdapter(this@ActionSheetFragment)
        recycler.setLayoutManager(LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recycler.setAdapter(actionSheetAdapter);
        recycler.setHasFixedSize(true);
        card_cancel.setOnClickListener(this@ActionSheetFragment);
    }

    override fun onActivityCreated(savedInstanceState : Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setItems();
    }

    override fun onClick(view : View) {
        when(view) {
            card_cancel -> {
                Log.d(TAG,"onClick Cancel");
                dismiss();
            }
            else -> {
                Log.d(TAG,"onClick default");
                Toast.makeText(getContext(), "Default", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onClickItem(actionSheetViewHolderModel : ActionSheetViewHolderModel, position : Int) {
        Log.d(TAG, "onClickItem($actionSheetViewHolderModel, $position");
        Toast.makeText(getContext(), "Action Button ${actionSheetViewHolderModel.data} clicked", Toast.LENGTH_SHORT).show();
        dismiss();
    }

    private fun setItems() {
        val list : MutableList<ActionSheetViewHolderModel> = mutableListOf<ActionSheetViewHolderModel>()
        list.clear()
        list.add(ActionSheetViewHolderModel("1"))
        list.add(ActionSheetViewHolderModel("2"))
        list.add(ActionSheetViewHolderModel("3"))
        list.add(ActionSheetViewHolderModel("4"))
        list.add(ActionSheetViewHolderModel("5"))
        //list.add(ActionSheetViewHolderModel("6"))
        //list.add(ActionSheetViewHolderModel("7"))
        //list.add(ActionSheetViewHolderModel("8"))
        //list.add(ActionSheetViewHolderModel("9"))
        //list.add(ActionSheetViewHolderModel("10"))
        //list.add(ActionSheetViewHolderModel("11"))
        //list.add(ActionSheetViewHolderModel("12"))
        //list.add(ActionSheetViewHolderModel("13"))
        //list.add(ActionSheetViewHolderModel("14"))
        //list.add(ActionSheetViewHolderModel("15"))
        //list.add(ActionSheetViewHolderModel("16"))
        //list.add(ActionSheetViewHolderModel("17"))
        //list.add(ActionSheetViewHolderModel("18"))
        //list.add(ActionSheetViewHolderModel("19"))
        //list.add(ActionSheetViewHolderModel("20"))
        actionSheetAdapter?.setItems(list)
    }
}