package com.fjmg.inventory.ui.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fjmg.inventory.R;
import com.fjmg.inventory.databinding.FragmentDashBoardBinding;

public class DashBoardFragment extends Fragment implements View.OnClickListener {

    FragmentDashBoardBinding binding;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case (R.id.action_setting_menu):
                NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_preferenceFragment);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentDashBoardBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        binding.ImgbtnDependecia.setOnClickListener(this);
        binding.ImgbtnAbout.setOnClickListener(this);
        binding.ImgbtnInventory.setOnClickListener(this);
        binding.ImgbtnProducts.setOnClickListener(this);
        binding.ImgbtnSecciones.setOnClickListener(this);
        binding.ImgbtnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case (R.id.ImgbtnAbout):
                ShowAboutUs();
                break;
            case (R.id.ImgbtnDependecia):
                ShowDependency();
                break;
            case (R.id.ImgbtnSecciones):
                break;
            case (R.id.ImgbtnInventory):
                ShowInventory();
                break;
            case (R.id.ImgbtnProducts):
                break;
            case (R.id.ImgbtnSettings):
                break;
        }
    }

    private  void ShowAboutUs()
    {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_aboutUsFragment);
    }
    private  void ShowInventory()
    {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_inventoryFragment);
    }
    private  void ShowDependency()
    {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_dependencyFragment);
    }
}