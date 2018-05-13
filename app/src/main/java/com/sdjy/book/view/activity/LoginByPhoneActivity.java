package com.sdjy.book.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.mvp.entity.User;
import com.sdjy.book.mvp.presenter.impl.LoginPresenter;
import com.sdjy.book.view.IRefresh;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginByPhoneActivity extends BaseActivity implements IUiListener, IRefresh<User> {

    private EditText phoneEt;
    private EditText codeEt;
    private Button codeBtn;
    private Button loginBtn;
    private CheckBox isReadCb;
    private TextView registerTv;
    private TextView loginTv;
    private ImageView qqLoginIv;
    private ImageView wxLoginIv;
    private CircleImageView profileCiv;
    private ConstraintLayout content;
    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;
    private LoginPresenter loginPresenter = new LoginPresenter(this);
    private UserInfo qqInfo;
    private String nickName = "";
    private String figureurl = "";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


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
        content = $(R.id.content);
        phoneEt = $(R.id.phone_et);
        codeEt = $(R.id.code_et);
        codeBtn = $(R.id.code_btn);
        loginBtn = $(R.id.login_btn);
        isReadCb = $(R.id.isread_cb);
        registerTv = $(R.id.register_tv);
        loginTv = $(R.id.login_tv);
        qqLoginIv = $(R.id.qqlogin_iv);
        wxLoginIv = $(R.id.wxlogin_iv);
        profileCiv = $(R.id.profile_civ);
        if (mTencent == null) {
            mTencent = Tencent.createInstance("1106812701", this.getApplicationContext());
        }
        if(!figureurl.equals("")) {
            Uri parse = Uri.parse(figureurl);
            profileCiv.setImageURI(parse);
        }
    }

    @Override
    protected void doBusiness(Context mContext) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        editor = sharedPreferences.edit();
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.code_btn:
                break;
            case R.id.login_btn:
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:");
                break;
            case R.id.register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.login_tv:
                startActivity(LoginActivity.class);
                break;
            case R.id.qqlogin_iv:
                if (!mTencent.isSessionValid()) {
                    mTencent.login(this, "all", this);
                    isServerSideLogin = false;
                    Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                } else {
                    if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                        mTencent.logout(this);
                        mTencent.login(this, "all", this);
                        isServerSideLogin = false;
                        Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                        return;
                    }
                    mTencent.logout(this);
                }
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

    @Override
    public void onComplete(Object o) {
        try {
            Log.d("BaseUiListener", "onComplete:");
            if (null == o) {
                return;
            }
            JSONObject jsonResponse = (JSONObject) o;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                return;
            }
            //此处为登陆成功提示，可根据业务逻辑需要自行更改。
            //可将返回参数里的openid用于唯一标识用户身份（每一个openid与QQ号码对应）。
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            String token = ((JSONObject) o).getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = ((JSONObject) o).getString(Constants.PARAM_EXPIRES_IN);
            final String openId = ((JSONObject) o).getString(Constants.PARAM_OPEN_ID);
            Log.d("SDKQQAgentPref", "PARAM_ACCESS_TOKEN:" + token);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
            qqInfo = new UserInfo(LoginByPhoneActivity.this, mTencent.getQQToken());
            qqInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {

                    try {
                        nickName = ((JSONObject) o).getString("nickname");
                        figureurl = ((JSONObject) o).getString("figureurl_qq_2");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("SDKQQAgentPref", "nickName:" + nickName);
                    loginPresenter.login(LoginByPhoneActivity.this, phoneEt.getText().toString(),nickName,"","2",
                            "",openId);
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {
        if (isServerSideLogin) {
            isServerSideLogin = false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(User user) {
        Uri parse = Uri.parse(figureurl);
        profileCiv.setImageURI(parse);
        //服务器获取
        editor.putString(Constant.USERNAME,user.getUsername());
        editor.putString(Constant.LOGINNAME,user.getLoginname());
        editor.putInt(Constant.AGE,user.getAge());
        editor.putString(Constant.SEX,user.getSex());
        editor.putString(Constant.USERTYPE,user.getUserType());
        editor.putInt(Constant.POINTS,user.getPoints());
        editor.putString(Constant.LASTLOGINDATE,user.getLastloginDate());
        editor.putString(Constant.TOKEN,user.getActionSession());
        //本机产生
        editor.putString(Constant.PHONE,phoneEt.getText().toString());
        editor.putString(Constant.QQ,nickName);
        editor.putString(Constant.PROFILE,figureurl);
        editor.apply();
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onFiled(String s) {
        Snackbar.make(content, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(int process) {

    }

    @Override
    public void onLoaded() {

    }
}
