package com.example.customviewapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class ActionSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener, ActionSheetListener {

    public static final String TAG = ActionSheetFragment.class.getSimpleName();
    Dialog dialog;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetBehavior bottomSheetBehavior = new BottomSheetBehavior<FrameLayout>();
    DisplayMetrics displayMetrics;
    int peekHeight;
    private RecyclerView recycler;
    private CardView btnCancel;
    private ActionSheetAdapter actionSheetAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.ActionSheetTheme);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                bottomSheetDialog = (BottomSheetDialog) dialog;
                bottomSheetBehavior = bottomSheetDialog.getBehavior();
                displayMetrics = getResources().getDisplayMetrics();
                peekHeight = (int) (displayMetrics.heightPixels * .80);
                bottomSheetBehavior.setPeekHeight(peekHeight);
            }
        });
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setDimAmount(1.0f);
        recycler = (RecyclerView)view.findViewById(R.id.recycler);
        btnCancel = (CardView)view.findViewById(R.id.card_cancel);
        actionSheetAdapter = new ActionSheetAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recycler.setAdapter(actionSheetAdapter);
        recycler.setHasFixedSize(true);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setItems();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_cancel:
                Log.d(TAG,"onClick Cancel");
                dismiss();
                break;
            default:
                Log.d(TAG,"onClick default");
                Toast.makeText(getContext(), "Default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClickItem(ActionSheetViewHolderModel actionSheetViewHolderModel, int position) {
        Log.d(TAG,"onClickItem(" + actionSheetViewHolderModel + ", " + position);
        dismiss();
    }

    private void setItems() {
        ArrayList<ActionSheetViewHolderModel> list = new ArrayList<ActionSheetViewHolderModel>();
        list.clear();
        list.add(new ActionSheetViewHolderModel("1"));
        list.add(new ActionSheetViewHolderModel("2"));
        list.add(new ActionSheetViewHolderModel("3"));
        list.add(new ActionSheetViewHolderModel("4"));
        list.add(new ActionSheetViewHolderModel("5"));
        //list.add(new ActionSheetViewHolderModel("6"));
        //list.add(new ActionSheetViewHolderModel("7"));
        //list.add(new ActionSheetViewHolderModel("8"));
        //list.add(new ActionSheetViewHolderModel("9"));
        //list.add(new ActionSheetViewHolderModel("10"));
        //list.add(new ActionSheetViewHolderModel("11"));
        //list.add(new ActionSheetViewHolderModel("12"));
        //list.add(new ActionSheetViewHolderModel("13"));
        //list.add(new ActionSheetViewHolderModel("14"));
        //list.add(new ActionSheetViewHolderModel("15"));
        //list.add(new ActionSheetViewHolderModel("16"));
        //list.add(new ActionSheetViewHolderModel("17"));
        //list.add(new ActionSheetViewHolderModel("18"));
        //list.add(new ActionSheetViewHolderModel("19"));
        //list.add(new ActionSheetViewHolderModel("20"));
        actionSheetAdapter.setItems(list);
    }
}
