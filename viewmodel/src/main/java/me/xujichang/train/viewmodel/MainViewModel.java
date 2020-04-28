package me.xujichang.train.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import me.xujichang.train.viewmodel.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.viewmodel.MainViewModel
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/14 6:15 PM
 * @since 1.0.0
 */
public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<User>> mUsersLiveData;
    private List<User> mUsers;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<User>> getUsersLiveData() {
        if (null == mUsers) {
            mUsersLiveData = new MutableLiveData<>();
            loadUsers();
        }
        return mUsersLiveData;
    }

    private void loadUsers() {

    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> pUsers) {
        mUsers = pUsers;
    }
}
