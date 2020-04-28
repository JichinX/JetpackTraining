package me.xujichang.train.workmanager.works;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import me.xujichang.train.workmanager.MainActivity;

public class LocationWorker extends Worker {

    private static final String TAG = "LocationWorker";

    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.i(TAG, "LocationWorker: " + workerParams.getInputData().getString(MainActivity.DATA));
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "doWork: ");
        return Result.success();
    }
}
