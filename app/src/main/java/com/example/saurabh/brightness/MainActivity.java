package com.example.saurabh.brightness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
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
                        Intent intent = new Intent(MainActivity.this, torch.class);
                        MainActivity.this.startActivity(intent);
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
