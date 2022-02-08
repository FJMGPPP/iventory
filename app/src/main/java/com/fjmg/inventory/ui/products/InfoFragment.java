package com.fjmg.inventory.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fjmg.inventory.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment
{
    FragmentInfoBinding binding;

    public static final String TAG = "ProductInfoFragment" ;

    public static InfoFragment newInstance(Bundle bundle) {
        InfoFragment myFragment = new InfoFragment();
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
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}