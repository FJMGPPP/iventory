package com.fjmg.inventory.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.fjmg.inventory.R;
import com.fjmg.inventory.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
{
    AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;
    Toolbar toolbar;
    NavController controller;
    @SuppressLint("UseSupportActionBar")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.content.toolBar) ;
        setContentView(binding.getRoot());
        //@todo cambiar imagen
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        controller = Navigation.findNavController(this,R.id.nav_host_fragment_main);
        //setupNavigationView();
        Set<Integer> topLevelDestionation = new HashSet<>();
        //Primer nivel sin flecha
        topLevelDestionation.add(R.id.inventoryFragment);
        topLevelDestionation.add(R.id.dependencyFragment);
        topLevelDestionation.add(R.id.aboutUsFragment);
        topLevelDestionation.add(R.id.preferenceFragment);
        topLevelDestionation.add(R.id.productFragment);
        topLevelDestionation.add(R.id.sectionsFragment);
        //Controlador del navegador
        NavigationUI.setupWithNavController(binding.navegation,controller);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestionation).setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,controller,appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(controller,appBarConfiguration) || super.onSupportNavigateUp();
    }


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if(pref.getKey().equals(getString(R.string.key_accounts))) {
            controller.navigate(R.id.action_preferenceFragment_to_accountsFragment);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            {
                super.onBackPressed();
            }
    }
}
