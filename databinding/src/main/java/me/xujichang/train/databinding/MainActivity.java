package me.xujichang.train.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import me.xujichang.train.databinding.databinding.ActivityMainBinding;
import me.xujichang.train.databinding.entity.InitData;
import me.xujichang.train.databinding.entity.ObservableUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private ObservableUser mObservableUser;
    private InitData mInitData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.
        //mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //2.
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mInitData = new InitData();
        mObservableUser = new ObservableUser();
        mBinding.setInitData(mInitData);
        mBinding.setUser(mObservableUser);
    }

    public void resetData(View view) {
        mObservableUser = new ObservableUser("奥特曼", 23, "青岛市区");
        mInitData = new InitData("Hello China！…");
        mBinding.setUser(mObservableUser);
        mBinding.setInitData(mInitData);
    }

    public void changeObservableObject(View view) {
        mInitData.setGreeting("Shit!");
        mObservableUser.setAddress("烟台市...");
        mObservableUser.setAge(33);
        mObservableUser.setName("李晨");
    }
}
