package me.xujichang.train.livedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MutableLiveData<String> mGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGreeting = new MutableLiveData<>();

        mGreeting.setValue("Hello");
        mGreeting.postValue("World");

        mGreeting.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String pS) {
                Log.i(TAG, "onChanged: ");
            }
        });
        Transformations.switchMap(mGreeting, new Function<String, LiveData<Integer>>() {
            @Override
            public LiveData<Integer> apply(String input) {
                return new MutableLiveData<>();
            }
        });
        Transformations.map(mGreeting, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }
}
