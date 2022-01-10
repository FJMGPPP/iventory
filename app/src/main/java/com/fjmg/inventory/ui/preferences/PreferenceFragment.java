package com.fjmg.inventory.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fjmg.inventory.R;
import com.fjmg.inventory.ui.MainActivity;

public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener
{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference preference = getPreferenceManager().findPreference(getString(R.string.key_help_point));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Se accedio a la web",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (key.equals(getString(R.string.key_rigtone)))
        {
            ListPreference listPreference = (ListPreference)preference;
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            if(index >= 0)
            {
                preference.setSummary(listPreference.getValue());
            }
            else
            {
                preference.setSummary(sharedPreferences.getString(key,""));
            }
        }
    }
}