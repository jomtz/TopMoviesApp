package com.josmartinez.topmoviesapp;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Add preferences defined in the XML file in res->xml->preferences
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        int count = preferenceScreen.getPreferenceCount();
        for (int i=0; i < count; i++){
            Preference p = preferenceScreen.getPreference(i);
        }
    }
}
