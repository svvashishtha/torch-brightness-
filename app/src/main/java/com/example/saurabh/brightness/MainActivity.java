package com.example.saurabh.brightness;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import java.security.Policy;


public class MainActivity extends Activity {

    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton button = (ToggleButton)findViewById(R.id.bright);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean state = button.isChecked();
                if(state) {
                    if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
                    {
                        Camera.Parameters p = camera.getParameters();
                        PackageManager pm=getApplicationContext().getPackageManager();
                        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(p);
                        camera.startPreview();

                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, torch.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
                button.setChecked(!state);
            }
        });








    }
    @Override
    public void onDestroy()
    {
        Settings.System.putInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        super.onPause();
    }
}
