package me.xujichang.train.camerax.video;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.VideoCapture;

/**
 * Info:for JetpackTraining  in me.xujichang.train.camerax.video.VideoRecordActivity
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/3/20 10:17 AM
 * @since 1.0.0
 */
public class VideoRecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageCapture vImageCapture = new ImageCapture.Builder().build();

    }

}
