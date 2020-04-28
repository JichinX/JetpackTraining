package me.xujichang.train.camerax;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;

/**
 * Info:for JetpackTraining  in me.xujichang.train.camerax.App
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/21 9:30 AM
 * @since 1.0.0
 */
public class App extends Application implements CameraXConfig.Provider {
    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return Camera2Config.defaultConfig();
    }
}
