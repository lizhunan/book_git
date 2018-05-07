package com.sdjy.book.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.app.BaseActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class LoginByPhoneActivity extends BaseActivity {

    private EditText phoneEt;
    private EditText codeEt;
    private Button codeBtn;
    private Button loginBtn;
    private CheckBox isReadCb;
    private TextView registerTv;
    private TextView loginTv;
    private ImageView qqLoginIv;
    private ImageView wxLoginIv;
    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;

    @Override
    protected void initParms(Bundle parms) {

    }

    @Override
    protected int bindLayout() {
        setSetStatusBar(true);
        return R.layout.activity_login;
    }

    @Override
    protected void initView(View view) {
        phoneEt = $(R.id.phone_et);
        codeEt = $(R.id.code_et);
        codeBtn = $(R.id.code_btn);
        loginBtn = $(R.id.login_btn);
        isReadCb = $(R.id.isread_cb);
        registerTv = $(R.id.register_tv);
        loginTv = $(R.id.login_tv);
        qqLoginIv = $(R.id.qqlogin_iv);
        wxLoginIv = $(R.id.wxlogin_iv);
        if (mTencent == null) {
            mTencent = Tencent.createInstance("1106812701", this.getApplicationContext());
        }
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.code_btn:
                break;
            case R.id.login_btn:
                break;
            case R.id.register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.login_tv:
                startActivity(LoginActivity.class);
                break;
            case R.id.qqlogin_iv:
                onClickLogin();
                break;
            case R.id.wxlogin_iv:

                break;
        }
    }

    @Override
    protected void setListener() {
        codeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        qqLoginIv.setOnClickListener(this);
        wxLoginIv.setOnClickListener(this);
    }

    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
        }
    };

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Log.d("BaseUiListener", "onComplete:");
            if (null == response) {
                showResultDialog(LoginByPhoneActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                showResultDialog(LoginByPhoneActivity.this, "返回为空", "登录失败");
                return;
            }
            //此处为登陆成功提示，可根据业务逻辑需要自行更改。
            //可将返回参数里的openid用于唯一标识用户身份（每一个openid与QQ号码对应）。
            showResultDialog(LoginByPhoneActivity.this, response.toString(), "登录成功");
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {
        }

        @Override
        public void onError(UiError e) {
        }

        @Override
        public void onCancel() {
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    public static final void showResultDialog(Context context, String msg,
                                              String title) {
        if (msg == null) return;
        String rmsg = msg.replace(",", "\n");
        Log.d("Util", rmsg);
        new AlertDialog.Builder(context).setTitle(title).setMessage(rmsg)
                .setNegativeButton("知道了", null).create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
