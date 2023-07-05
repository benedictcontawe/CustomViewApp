package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FilterFragment extends BaseFragment implements ChipGroup.OnCheckedStateChangeListener {

    private static final String TAG = FilterFragment.class.getSimpleName();

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private FilterAdapter adapter;

    public FilterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getMainActivity()).get(MainViewModel.class);
        adapter = new FilterAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        adapter.setItems( viewModel.getFilterModelList() );
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipGroup = view.findViewById(R.id.chip_group);
        recyclerView = view.findViewById(R.id.recycler_view);
        chipGroup.setOnCheckedStateChangeListener(this::onCheckedChanged);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        if (chipGroup == group && checkedIds.isEmpty() == false) {
            for (Integer checkedId : checkedIds) {
                Chip chip = group.findViewById(checkedId);
                Log.d(TAG,"Chip " + chip.getText() + " " + chip.isChecked());
                viewModel.initializeFilterList();
                viewModel.filterList( chip.getText().toString() );
                adapter.setItems( viewModel.getFilterModelList() );
            }
        } else {
            viewModel.initializeFilterList();
            adapter.setItems( viewModel.getFilterModelList() );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.initializeFilterList();
    }
}