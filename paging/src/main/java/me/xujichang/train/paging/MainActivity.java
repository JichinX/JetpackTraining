package me.xujichang.train.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import me.xujichang.train.paging.adapter.UserAdapter;
import me.xujichang.train.paging.databinding.ActivityMainBinding;
import me.xujichang.train.paging.entity.User;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding mBinding;
    private UserAdapter mAdapter;
    private MainViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        mModel = new ViewModelProvider(this).get(MainViewModel.class);

        mAdapter = new UserAdapter();

        mModel.getUsers().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> pUsers) {
                Log.i(TAG, "onChanged: " + pUsers);
                mAdapter.submitList(pUsers);
            }
        });
        mModel.getLoadStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean pBoolean) {
                mBinding.swipe.setRefreshing(pBoolean);
            }
        });
        mBinding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvUsers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mBinding.rvUsers.setAdapter(mAdapter);

        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mModel.invalidateDataSource();
            }
        });
    }
}
