package me.xujichang.train.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.util.List;

import me.xujichang.train.viewmodel.entity.User;
import me.xujichang.train.viewmodel.fragments.OneViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //since 2.2.0
        mModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mViewModel = ViewModelProviders.of(this).get(OneViewModel.class);

        mModel.getUsersLiveData()
                .observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> pUsers) {

                    }
                });
    }
}
