package me.xujichang.train.paging.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.xujichang.train.paging.Const;
import me.xujichang.train.paging.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.data.UserPagedDataSource
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/15 9:18 AM
 * @since 1.0.0
 */
public class UserPagedDataSource extends PageKeyedDataSource<Long, User> {

    private static final String TAG = "UserPagedDataSource";
    private MutableLiveData<Boolean> mLoading;

    public UserPagedDataSource(MutableLiveData<Boolean> pLoading) {
        mLoading = pLoading;
        addInvalidatedCallback(new InvalidatedCallback() {
            @Override
            public void onInvalidated() {
                mLoading.postValue(false);
            }
        });
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, User> callback) {
        mLoading.postValue(true);
        Log.i(TAG, "loadInitial: " + params.requestedLoadSize + " " + Thread.currentThread());
        List<User> vUsers = getUsers(1);
        callback.onResult(vUsers, 1L, 2L);
        mLoading.postValue(false);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, User> callback) {
        Log.i(TAG, "loadBefore: " + Thread.currentThread());
        //>..
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, User> callback) {
        mLoading.postValue(true);
        Log.i(TAG, "loadAfter: " + params.requestedLoadSize + "  " + Thread.currentThread());
        List<User> vUsers = getUsers(params.key);
        callback.onResult(vUsers, params.key + 1);
        mLoading.postValue(false);
    }

    private List<User> getUsers(long pI) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException pE) {
            pE.printStackTrace();
        }
        List<User> vUsers = new ArrayList<>();
        if (pI <= 20) {
            for (int index = 0; index < Const.PAGE_SIZE; index++) {
                vUsers.add(new User((pI - 1) * Const.PAGE_SIZE + index));
            }
        }
        return vUsers;
    }
}
