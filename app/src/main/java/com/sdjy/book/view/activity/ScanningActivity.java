package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.sdjy.book.mvp.presenter.impl.StartInputPresenter;
import com.sdjy.book.view.IRefresh;

public class ScanningActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener  {

    private CaptureManager captureManager;
    private boolean isLightOn = false;
    private DecoratedBarcodeView scanningCamera;
    private ImageView inputMCodeIv;
    private ImageView flashlightIv;
    private ImageView closeIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        captureManager = new CaptureManager(this, scanningCamera);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setmAllowFullScreen(true);
        return R.layout.activity_scanning;
    }

    @Override
    protected void initView(View view) {
        scanningCamera = $(R.id.scanning_camera);
        inputMCodeIv = $(R.id.input_iv);
        flashlightIv = $(R.id.flashlight_iv);
        closeIv = $(R.id.close_iv);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.input_iv:
                break;
            case R.id.flashlight_iv:
                if (isLightOn) {
                    scanningCamera.setTorchOff();
                } else {
                    scanningCamera.setTorchOn();
                }
                break;
            case R.id.close_iv:
                finish();
                break;
        }
    }

    @Override
    protected void setListener() {
        scanningCamera.setTorchListener(this);
        inputMCodeIv.setOnClickListener(this);
        flashlightIv.setOnClickListener(this);
        closeIv.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onTorchOn() {
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        isLightOn = false;
    }

}
