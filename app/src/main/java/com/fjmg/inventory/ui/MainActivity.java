package com.fjmg.inventory.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fjmg.inventory.R;
import com.fjmg.inventory.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
{

    ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_about_us_menu:
                Toast.makeText(this,"se ha pulsado Aboutus",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_setting_menu:
                Toast.makeText(this,"se ha pulsado Settings",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment_main);
        if(pref.getKey().equals(getString(R.string.key_accounts))) {
            navController.navigate(R.id.action_preferenceFragment_to_accountsFragment);
        }
        return true;
    }
}
