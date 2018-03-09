package com.sdjy.book.view.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sdjy.book.R;

/**
 * 设置fragment
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener  {


    public SettingsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
