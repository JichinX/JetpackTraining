//package me.xujichang.train.camerax.image;
//
//import android.content.Context;
//import android.hardware.display.DisplayManager;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.Surface;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.camera.core.AspectRatio;
//import androidx.camera.core.Camera;
//import androidx.camera.core.CameraSelector;
//import androidx.camera.core.ImageAnalysis;
//import androidx.camera.core.ImageCapture;
//import androidx.camera.core.Preview;
//import androidx.camera.core.VideoCapture;
//import androidx.camera.core.impl.VideoCaptureConfig;
//import androidx.camera.lifecycle.ProcessCameraProvider;
//import androidx.camera.view.PreviewView;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.core.content.ContextCompat;
//import androidx.core.hardware.display.DisplayManagerCompat;
//
//import com.google.common.util.concurrent.ListenableFuture;
//
//import java.util.concurrent.ExecutionException;
//
//import me.xujichang.train.camerax.MainActivity;
//import me.xujichang.train.camerax.R;
//import me.xujichang.train.camerax.databinding.ActivityImageCaptureBinding;
//
//import static java.lang.Math.abs;
//import static java.lang.Math.max;
//import static java.lang.Math.min;
//
///**
// * Info:for JetpackTraining  in me.xujichang.train.camerax.image.ImageCaptureActivity
// * Des:
// *
// * @author xujichang
// * @version 1.0.0
// * @date 2020/3/20 10:22 AM
// * @since 1.0.0
// */
//public class ImageCaptureActivity extends AppCompatActivity {
//
//
//    private static final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
//    private static final String PHOTO_EXTENSION = ".jpg";
//    private static final double RATIO_4_3_VALUE = 4.0 / 3.0;
//    private static final double RATIO_16_9_VALUE = 16.0 / 9.0;
//    private int lensFacing = CameraSelector.LENS_FACING_BACK;
//
//
//    private static final String TAG = "ImageCaptureActivity";
//    private PreviewView mViewFinder;
//    private ActivityImageCaptureBinding mBinding;
//    private int mDisplayId = -1;
//    private ConstraintLayout mContainer;
//    private DisplayManager mDisplayManager;
//    private DisplayManager.DisplayListener mDisplayListener;
//
//    private ImageCapture mImageCapture;
//    private ImageAnalysis mImageAnalysis;
//
//    private Preview mPreview;
//    private Camera mCamera;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mBinding = ActivityImageCaptureBinding.inflate(getLayoutInflater());
//        setContentView(mBinding.getRoot());
//        mViewFinder = mBinding.viewFinder;
//        mContainer = mBinding.container;
//
//        mDisplayListener = new DisplayManager.DisplayListener() {
//            @Override
//            public void onDisplayAdded(int displayId) {
//
//            }
//
//            @Override
//            public void onDisplayRemoved(int displayId) {
//
//            }
//
//            @Override
//            public void onDisplayChanged(int displayId) {
//                int rotation = mBinding.getRoot().getDisplay().getRotation();
//                Log.i(TAG, "onDisplayChanged: " + displayId + " " + rotation);
//                if (displayId == mDisplayId) {
//                    mImageCapture.setTargetRotation(rotation);
//                    mImageAnalysis.setTargetRotation(rotation);
//                }
//            }
//        };
//        mViewFinder.post(new Runnable() {
//            @Override
//            public void run() {
//                mDisplayId = mViewFinder.getDisplay().getDisplayId();
//                updateCameraUi();
//                bindCameraUseCases();
//            }
//        });
//        mDisplayManager.registerDisplayListener(mDisplayListener, null);
//
//    }
//
//    private void updateCameraUi() {
//        View vView = mContainer.findViewById(R.id.camera_controls);
//        if (null != vView){
//            mContainer.removeView(vView);
//        }
//        View cameraControls = View.inflate(this, R.layout.camera_controls, mContainer);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }
//
//    private void bindCameraUseCases() {
//        DisplayMetrics vMetrics = new DisplayMetrics();
//        mViewFinder.getDisplay().getRealMetrics(vMetrics);
//
//        int screenAspectRatio = aspectRatio(vMetrics.widthPixels, vMetrics.heightPixels);
//        int ratio = mViewFinder.getDisplay().getRotation();
//
//        CameraSelector vSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();
//        ListenableFuture<ProcessCameraProvider> vFuture = ProcessCameraProvider.getInstance(this);
//
//        vFuture.addListener(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ProcessCameraProvider vProvider = vFuture.get();
//                    mPreview = new Preview
//                            .Builder()
//                            .setTargetAspectRatio(screenAspectRatio)
//                            .setTargetRotation(ratio)
//                            .build();
//                    mPreview.setSurfaceProvider(mViewFinder.getPreviewSurfaceProvider());
//
//                    mImageCapture = new ImageCapture
//                            .Builder()
//                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
//                            .setTargetAspectRatio(screenAspectRatio)
//                            .setTargetRotation(ratio)
//                            .build();
////                    mImageAnalysis = new ImageAnalysis
////                            .Builder()
////                            .setTargetAspectRatio(screenAspectRatio)
////                            .setTargetRotation(ratio)
////                            .build();
////                    mImageAnalysis.setAnalyzer();;
//
//                    vProvider.unbindAll();
//                    mCamera = vProvider.bindToLifecycle(ImageCaptureActivity.this, vSelector, mPreview, mImageCapture /*mImageAnalysis*/);
//                } catch (ExecutionException | InterruptedException pE) {
//                    pE.printStackTrace();
//                }
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }
//
//    private int aspectRatio(int width, int height) {
//        double previewRation = ((double) max(width, height)) / min(width, height);
//        if (abs(previewRation - RATIO_4_3_VALUE) <= abs(previewRation - RATIO_16_9_VALUE)) {
//            return AspectRatio.RATIO_4_3;
//        }
//        return AspectRatio.RATIO_16_9;
//    }
//
//}
