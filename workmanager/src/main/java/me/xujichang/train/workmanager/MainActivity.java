package me.xujichang.train.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;

import android.os.Bundle;
import android.util.Log;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import me.xujichang.train.workmanager.works.LocationWorker;
import me.xujichang.train.workmanager.works.UploadWorker;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //执行条件
        Constraints vConstraints = new Constraints
                .Builder()
//                .setRequiresStorageNotLow(true)
//                .setRequiresBatteryNotLow(true)
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresCharging(true)
                .build();
        //数据
        Data vData = new Data
                .Builder()
                .putString(DATA, "This is data from Main")
                .build();
        //创建
        WorkRequest vRequest = new OneTimeWorkRequest
                .Builder(UploadWorker.class)
//                .setInitialDelay(2000, TimeUnit.MILLISECONDS)
//                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .setInputData(vData)
                .setConstraints(vConstraints)
                .build();

        /**1,{@link WorkSpec#setPeriodic(long)} {@link WorkSpec#setPeriodic(long, long)}
         * 2,{@link PeriodicWorkRequest.Builder#Builder(Class, Duration, Duration)}*/
        WorkRequest vPeriodRequest = new PeriodicWorkRequest
                .Builder(LocationWorker.class, 2000, TimeUnit.MILLISECONDS, 2000, TimeUnit.MILLISECONDS)
//                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .setInputData(vData)
                .setConstraints(vConstraints)
                .build();
        //监听状态
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(vRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo pInfo) {
                        Data progressData = pInfo.getProgress();
                        Log.i(TAG, "onChanged: " + progressData.getInt(UploadWorker.PROGRESS, -1) + "  " + pInfo.getState());
                    }
                });
        //加入队列
        WorkManager.getInstance(this).cancelAllWork();
        WorkManager.getInstance(this).enqueue(vRequest);
        WorkManager.getInstance(this).enqueue(vPeriodRequest);
    }
}
