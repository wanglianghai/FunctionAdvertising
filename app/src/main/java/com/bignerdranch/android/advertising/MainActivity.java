package com.bignerdranch.android.advertising;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EntryAdvertising{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AdvertisingView advertisingView = (AdvertisingView) findViewById(R.id.custom_view);
        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advertisingView.startAnimator();
                AdvertisingDialog dialog = new AdvertisingDialog(MainActivity.this);
                dialog.setAdvertising(MainActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void showAdvertising() {
        Toast.makeText(this, "show advertising", Toast.LENGTH_SHORT).show();
    }
}
