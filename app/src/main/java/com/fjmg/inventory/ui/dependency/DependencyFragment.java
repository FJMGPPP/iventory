package com.fjmg.inventory.ui.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.inventory.R;
import com.fjmg.inventory.databinding.FragmentDependencyBinding;
import com.fjmg.inventory.databinding.FragmentInventoryBinding;

public class DependencyFragment extends Fragment
{
    FragmentDependencyBinding binding;
    DependecyAdapter adapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_order_dependecy:
                Toast.makeText(getActivity(),"se ha pulsado ordenar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentlist_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentDependencyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        initRvDependency();
        super.onViewCreated(view, savedInstanceState);
    }
    /**
     Este metodo inicia el componente recycle view
     **/
    private void initRvDependency()
    {
        //1. Iniciar adaptadr
        adapter = new DependecyAdapter();
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.rvDependency.setAdapter(adapter);
    }
}
