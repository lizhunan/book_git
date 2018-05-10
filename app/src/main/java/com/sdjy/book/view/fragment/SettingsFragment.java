package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sdjy.book.R;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;
import com.sdjy.book.util.CacheManager;
import com.sdjy.book.view.activity.LoginByPhoneActivity;

/**
 * 设置fragment
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener {

    private Preference logoutPre;
    private Preference cachePre;
    private SwitchPreference netModeSwiPre;
    private SharedPreferences settingsPreference;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SettingsFragment() {
        settingsPreference = PreferenceManager.getDefaultSharedPreferences(BookApplication.getContext());
        editor = settingsPreference.edit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            addPreferencesFromResource(R.xml.pref_settings);
            logoutPre = this.findPreference(Constant.LOGOUT_PRE_KEY);
            cachePre = this.findPreference(Constant.CACHE_PRE_KEY);
            netModeSwiPre = (SwitchPreference) this.findPreference(Constant.SWIPRENETMODE_PRE_KEY);
            cachePre.setSummary(getResources().getString(R.string.pref_summary_clearcache_settings) +
                    "(" + CacheManager.getCacheSize(BookApplication.getContext().getCacheDir()) + ")");
            logoutPre.setOnPreferenceClickListener(this);
            cachePre.setOnPreferenceClickListener(this);
            netModeSwiPre.setOnPreferenceChangeListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch (preference.getKey()) {
            case Constant.SWIPRENETMODE_PRE_KEY:

                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case Constant.LOGOUT_PRE_KEY:
                editor.putString(Constant.TOKEN, "");
                editor.commit();
                startActivity(new Intent(getActivity(), LoginByPhoneActivity.class));
                getActivity().finish();
                break;
            case Constant.CACHE_PRE_KEY:
                CacheManager.cleanApplicationData(Constant.MAP_CACHE);
                break;
        }
        return false;
    }
}
