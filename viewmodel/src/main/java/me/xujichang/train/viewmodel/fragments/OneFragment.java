package me.xujichang.train.viewmodel.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.xujichang.train.viewmodel.MainViewModel;
import me.xujichang.train.viewmodel.R;
import me.xujichang.train.viewmodel.entity.User;

public class OneFragment extends Fragment {

    private static final String TAG = "OneFragment";
    private OneViewModel mViewModel;
    private MainViewModel mMainViewModel;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OneViewModel.class);
        OneViewModel mThisViewModel = ViewModelProviders.of(requireActivity()).get(OneViewModel.class);

        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        MainViewModel mActivityViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Log.i(TAG, "onActivityCreated: " + mViewModel);
        Log.i(TAG, "onActivityCreated: " + mThisViewModel);
        Log.i(TAG, "onActivityCreated: " + mMainViewModel);
        Log.i(TAG, "onActivityCreated: " + mActivityViewModel);
    }
}
