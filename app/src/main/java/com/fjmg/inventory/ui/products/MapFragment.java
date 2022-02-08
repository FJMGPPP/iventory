package com.fjmg.inventory.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fjmg.inventory.databinding.FragmentMapBinding;

public class MapFragment extends Fragment
{
    FragmentMapBinding binding;
    public static final String TAG = "ProductMapFragment" ;

    public static MapFragment newInstance(Bundle bundle) {
        MapFragment myFragment = new MapFragment();
        if (bundle != null)
        {
            myFragment.setArguments(bundle);
        }
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}