package me.xujichang.train.paging.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import me.xujichang.train.paging.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.data.UserDataSourceFactory
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/15 9:19 AM
 * @since 1.0.0
 */
public class UserDataSourceFactory extends DataSource.Factory<Long, User> {
    private MutableLiveData<Boolean> mLoading;

    public UserDataSourceFactory(MutableLiveData<Boolean> pLoading) {
        mLoading = pLoading;
    }

    @NonNull
    @Override
    public DataSource<Long, User> create() {
        return new UserPagedDataSource(mLoading);
    }
}
