package me.xujichang.train.camerax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import me.xujichang.train.camerax.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding mBinding;

    private ImageCapture mImageCapture;
    private OrientationEventListener mOrientationEventListener;
    private ProcessCameraProvider mCameraProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ListenableFuture<ProcessCameraProvider> vFuture = ProcessCameraProvider.getInstance(this);
        vFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    mCameraProvider = vFuture.get();
                    //Preview
                    Preview vPreview = new Preview
                            .Builder()
                            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                            .build();
                    //ImageCapture
                    mImageCapture = new ImageCapture
                            .Builder()
//                            .setTargetRotation(mBinding.getRoot().getDisplay().getRotation())
                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                            .build();
                    //ImageAnalysis
                    ImageAnalysis vAnalysis = new ImageAnalysis
                            .Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .setTargetResolution(new Size(1280, 720))
                            .build();
                    vAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), (ImageProxy image) -> {

                    });
                    // LocalTime.now();
                    //CameraSelector
                    CameraSelector vSelector = new CameraSelector
                            .Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build();
                    mCameraProvider.unbindAll();
                    //buildCamera
                    Camera vCamera = mCameraProvider.bindToLifecycle(getOwner(), vSelector, vPreview, mImageCapture);

                    vPreview.setSurfaceProvider(mBinding.previewView.createSurfaceProvider(vCamera.getCameraInfo()));

                    mOrientationEventListener = new OrientationEventListener(getOwner()) {
                        @Override
                        public void onOrientationChanged(int orientation) {
                            int rotation;
                            int display = mBinding.getRoot().getDisplay().getRotation();
                            if (orientation >= 45 && orientation < 135) {
                                rotation = Surface.ROTATION_270;
                            } else if (orientation >= 135 && orientation < 225) {
                                rotation = Surface.ROTATION_180;
                            } else if (orientation >= 225 && orientation < 315) {
                                rotation = Surface.ROTATION_90;
                            } else {
                                rotation = Surface.ROTATION_0;
                            }
                            Log.i(TAG, "onOrientationChanged: " + orientation + "   " + display + "   " + rotation);
                            mImageCapture.setTargetRotation(rotation);
                        }
                    };
                    mOrientationEventListener.enable();

                } catch (ExecutionException | InterruptedException pE) {
                    pE.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));

        mBinding.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mImageCapture.takePicture(ContextCompat.getMainExecutor(getOwner()), new ImageCapture.OnImageCapturedCallback() {
//                    @Override
//                    public void onCaptureSuccess(@NonNull ImageProxy image) {
//                        super.onCaptureSuccess(image);
//                    }
//
//                    @Override
//                    public void onError(@NonNull ImageCaptureException exception) {
//                        super.onError(exception);
//                    }
//                });
                File vFile = new File(getExternalFilesDir(Environment.DIRECTORY_DCIM), String.format("%s.jpg", String.valueOf(System.currentTimeMillis())));
                ImageCapture.OutputFileOptions vOptions = new ImageCapture.OutputFileOptions
                        .Builder(vFile)
                        .build();

                mImageCapture.takePicture(vOptions, Executors.newSingleThreadExecutor(), new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Log.i(TAG, "onImageSaved: " + outputFileResults.getSavedUri());
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.i(TAG, "onError: " + exception.getImageCaptureError());
                    }
                });
                mImageCapture.takePicture(Executors.newSingleThreadExecutor(), new ImageCapture.OnImageCapturedCallback() {
                    @Override
                    public void onCaptureSuccess(@NonNull ImageProxy image) {
                        super.onCaptureSuccess(image);
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        super.onError(exception);
                    }
                });
            }
        });

    }

    public AppCompatActivity getOwner() {
        return this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mCameraProvider) {
            mCameraProvider.unbind();
        }
        if (null != mOrientationEventListener) {
            mOrientationEventListener.disable();
        }
    }
}
