package com.fjmg.inventory.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.fjmg.inventory.R;
import com.fjmg.inventory.databinding.FragmentInventoryBinding;
import com.fjmg.inventory.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment
{
    FragmentProductBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(InfoFragment.newInstance(null));
    }

    private void loadFragment(Fragment newInstance)
    {
        if (newInstance!= null)
        {
            getChildFragmentManager().beginTransaction().replace(R.id.productContent,newInstance).commit();
        }
    }

    private void initNavigation()
    {
        binding.bottomNavigation.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId())
            {
                case R.id.info:
                    loadFragment(InfoFragment.newInstance(null));
                    break;
                case R.id.descripcion:
                    loadFragment(DescripcionFragment.newInstance(null));
                    break;
                case R.id.mapa:
                    loadFragment(MapFragment.newInstance(null));
                    break;
            }
            return  true;
        });
    }

}