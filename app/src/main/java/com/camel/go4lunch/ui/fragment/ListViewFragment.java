package com.camel.go4lunch.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.camel.go4lunch.BaseFragment;
import com.camel.go4lunch.R;

import com.camel.go4lunch.databinding.FragmentListViewBinding;
import com.camel.go4lunch.injection.Injection;
import com.camel.go4lunch.injection.ViewModelFactory;
import com.camel.go4lunch.models.Place;
import com.camel.go4lunch.ui.SharedViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListViewFragment extends BaseFragment {

    private FragmentListViewBinding mBinding;
    private ListViewPlacesAdapter mAdapter;


    public ListViewFragment() {}

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViewModel();
    }

    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel.class);
        sharedViewModel.getPlaceListLiveData().observe(this, this::getPlaceResults);
    }

    private void getPlaceResults(List<Place> places) {

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentListViewBinding.inflate(getLayoutInflater());

        configureRecyclerView();

        return mBinding.getRoot();
    }

    private void configureRecyclerView() {
        mAdapter = new ListViewPlacesAdapter(requireContext());
        mBinding.listViewFragmentRecyclerView.setAdapter(mAdapter);
        mBinding.listViewFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(new ColorDrawable(getResources().getColor(R.color.very_light_grey)));
        mBinding.listViewFragmentRecyclerView.addItemDecoration(itemDecoration);
    }
}
