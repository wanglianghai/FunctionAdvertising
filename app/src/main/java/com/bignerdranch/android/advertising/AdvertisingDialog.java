package com.bignerdranch.android.advertising;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Administrator on 2017/8/18/018.
 */

public class AdvertisingDialog extends Dialog implements AdvertisingAnimator{
    private EntryAdvertising mAdvertising;

    public AdvertisingDialog(@NonNull Context context) {
        super(context, R.style.DialogFullscreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_advertising);

        AdvertisingView view = (AdvertisingView) findViewById(R.id.custom_view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.setAnimator(this);
        view.startAnimator();

        findViewById(R.id.advertising_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdvertising != null) {
                    mAdvertising.showAdvertising();
                    dismiss();
                }
            }
        });
    }

    @Override
    public void finish() {
        dismiss();
    }

    public void setAdvertising(EntryAdvertising advertising) {
        mAdvertising = advertising;
    }
}
