package me.xujichang.train.paging;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;
import androidx.room.Database;

import io.reactivex.Observable;
import me.xujichang.train.paging.data.UserDataSourceFactory;
import me.xujichang.train.paging.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.MainViewModel
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/14 7:18 PM
 * @since 1.0.0
 */
public class MainViewModel extends ViewModel {
    private final LiveData<PagedList<User>> mUsers;
//    private final Observable<PagedList<User>> mRxUsers;
    private DataSource<Long, User> mDataSource;
    private MutableLiveData<Boolean> mLoading;

    public MainViewModel() {
        this(null);
    }

    public MainViewModel(Application pApplication) {
//        super(pApplication);
//        UserDao mDao = PageDatabase.getInstance(pApplication).userDao();
//        DataSource.Factory<Long, User> vFactory = mDao.getUsers();
//        mDataSource = vFactory.create();
        mLoading = new MutableLiveData<>();
        DataSource.Factory<Long, User> vFactory = new UserDataSourceFactory(mLoading);

        mDataSource = vFactory.create();
        mUsers = new LivePagedListBuilder<>(vFactory, Const.PAGE_SIZE).build();
//        mRxUsers = new RxPagedListBuilder<>(vFactory, Const.PAGE_SIZE).buildObservable();
    }

    public LiveData<PagedList<User>> getUsers() {
        return mUsers;
    }

//    public Observable<PagedList<User>> getRxUsers() {
//        return mRxUsers;
//    }

    public void invalidateDataSource() {
        mDataSource.invalidate();
    }

    public LiveData<Boolean> getLoadStatus() {
        return mLoading;
    }
}
