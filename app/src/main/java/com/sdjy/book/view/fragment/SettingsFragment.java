package com.sdjy.book.view.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
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

/**
 * 设置fragment
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener {

    private Preference logoutPre;
    private Preference cachePre;

    public SettingsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            addPreferencesFromResource(R.xml.pref_settings);
            logoutPre = this.findPreference(Constant.LOGOUT_PRE_KEY);
            cachePre = this.findPreference(Constant.CACHE_PRE_KEY);
            cachePre.setSummary(getResources().getString(R.string.pref_summary_clearcache_settings) +
                    "(" + CacheManager.getCacheSize(BookApplication.getContext().getCacheDir()) + ")");
            logoutPre.setOnPreferenceClickListener(this);
            cachePre.setOnPreferenceClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case Constant.LOGOUT_PRE_KEY:
                getActivity().finish();
                break;
            case Constant.CACHE_PRE_KEY:

                break;
        }
        return false;
    }
}
