package me.xujichang.train.workmanager.works;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import me.xujichang.train.workmanager.MainActivity;

/**
 * Info:for JetpackTraining  in me.xujichang.train.workmanager.works.UploadWorker
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/16 3:15 PM
 * @since 1.0.0
 */
public class UploadWorker extends Worker {
    public static final String PROGRESS = "progress";
    private static final String TAG = "UploadWorker";

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.i(TAG, "UploadWorker: " + workerParams.getInputData().getString(MainActivity.DATA));
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "doWork: ");
        int i = 0;
        while (i <= 100) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException pE) {
                pE.printStackTrace();
            }
//            Log.i(TAG, "doWork: " + i);
            setProgressAsync(new Data.Builder().putInt(PROGRESS, i).build());
            i++;
        }
        return Result.success();
    }
}
