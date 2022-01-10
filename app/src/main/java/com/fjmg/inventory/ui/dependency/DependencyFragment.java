package com.fjmg.inventory.ui.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.inventory.InventoryApplication;
import com.fjmg.inventory.R;

import com.fjmg.inventory.base.BaseDialogFragment;
import com.fjmg.inventory.base.DependencyOrdenator;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.databinding.FragmentDependencyBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.components.Dependency;

import java.util.ArrayList;
import java.util.Random;

public class DependencyFragment extends Fragment implements DependencyListContract.View, DependencyListContract.Adapter.onManageDepedencyLister {
    FragmentDependencyBinding binding;
    DependecyAdapter adapter;
    private DependencyListContract.Presenter presenter;
    boolean reverse = false;
    Dependecy affterModicateDependecy;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_order_dependecy:
                adapter.order();
                reverse = !reverse;
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
        presenter = new DependencyPresenter(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
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
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
    }

    /**
     Este metodo inicia el componente recycle view
     **/
    private void initRvDependency()
    {
        //1. Iniciar adaptadr
        adapter = new DependecyAdapter(new ArrayList<>(),this);
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.rvDependency.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.rvDependency.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        binding.progressCircularDependency.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        binding.progressCircularDependency.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(ArrayList list)
    {
        adapter.update(list);
        binding.AlertDepedencyData.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoData()
    {
        binding.AlertDepedencyData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDeleteSuccess(String message)
    {
        Snackbar undoBack = Snackbar.make(this.getView(),message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        undoBack.setAction("Desacer",view1 -> {
            presenter.undo(affterModicateDependecy);
            Toast.makeText(getContext(),"se desizo el cambio",Toast.LENGTH_LONG).show();
        });
        undoBack.show();
        if (affterModicateDependecy != null)
        {
            adapter.delete(affterModicateDependecy);
        }
        if (adapter.isEmpty)
        {
            binding.AlertDepedencyData.setVisibility(View.VISIBLE);
        }
        //Notificar de que se elimino una depdendencia
        Bundle paquete = new Bundle();
        paquete.putSerializable(affterModicateDependecy.TAG,affterModicateDependecy);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dependencyFragment)
                .createPendingIntent(); //.setArguments(bundle) para pasar parametro, debe llamarse igual los argumentos que el safeArgs
        Notification.Builder builder = new Notification.Builder(getContext(), InventoryApplication.IDCHANEL)
                .setSmallIcon(R.drawable.ic_delete_ico)
                .setAutoCancel(true)
                .setContentTitle("Se Elimino una dependencia")
                .setContentText("El elimino la dedependencia:" + affterModicateDependecy.getName())
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(100),builder.build());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateSuccess(String message)
    {
        Snackbar undoBack = Snackbar.make(this.getView(),message, BaseTransientBottomBar.LENGTH_INDEFINITE);
        undoBack.setAction("Desacer",view1 -> {
            presenter.undo(affterModicateDependecy);
            Toast.makeText(getContext(),"se desizo el cambio",Toast.LENGTH_LONG).show();
        });
        undoBack.show();
        if (affterModicateDependecy != null)
        {
            adapter.update(affterModicateDependecy);
        }
        if (adapter.isEmpty)
        {
            binding.AlertDepedencyData.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onUndoSuccess(String message)
    {
        adapter.undo(affterModicateDependecy);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void OnEditDepedency(Dependecy dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE,"Modificando depedencia");
        bundle.putString(BaseDialogFragment.MESSAGE,"¿Quieres modificar la dependencia?");
        NavHostFragment.findNavController(DependencyFragment.this).navigate(R.id.action_dependencyFragment_to_baseDialogFragment, bundle);
        //Una de las claves para realizar la comunicación entre fragmentos (padre-hijo) es utilizar los métodos supportFragmentManager
        // de la actividad para realizar el intercambio de información. //IMPORTANTE GETSUPPORTFRAGMENTMANAGER
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                //Si la respuesta es true en deletedDependency se procede con el caso de uso DELETE
                if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE))
                {
                    affterModicateDependecy = dependency;
                    presenter.update(dependency);
                }
            }
        });
    }
    @Override
    public void OnDeleteDepedency(Dependecy dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_dependency));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_dependency), dependency.getShortname()));
        NavHostFragment.findNavController(DependencyFragment.this).navigate(R.id.action_dependencyFragment_to_baseDialogFragment, bundle);
        //Una de las claves para realizar la comunicación entre fragmentos (padre-hijo) es utilizar los métodos supportFragmentManager
        // de la actividad para realizar el intercambio de información. //IMPORTANTE GETSUPPORTFRAGMENTMANAGER
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                //Si la respuesta es true en deletedDependency se procede con el caso de uso DELETE
                if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE))
                {
                    affterModicateDependecy = dependency;
                    presenter.delete(dependency);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
