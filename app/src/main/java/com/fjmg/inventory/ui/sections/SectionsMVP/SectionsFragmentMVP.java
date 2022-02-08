package com.fjmg.inventory.ui.sections.SectionsMVP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fjmg.inventory.R;
import com.fjmg.inventory.data.dao.SectionDAO;
import com.fjmg.inventory.data.model.Section;
import com.fjmg.inventory.databinding.FragmentSectionsMvpBinding;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependecyAdapterSections;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsContract;
import com.fjmg.inventory.ui.sections.DependencyAdapterMVP.DependencyAdapterSectionsPresenter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SectionsFragmentMVP extends Fragment implements  DependencyAdapterSectionsContract.View , SectionsFragmentMVPContract.View
{
    FragmentSectionsMvpBinding binding;
    DependecyAdapterSections adapterdepedencys ;
    DependencyAdapterSectionsContract.Presenter dependencyPresenter;
    SectionsFragmentMVPContract.Presenter sectionPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentSectionsMvpBinding.inflate(inflater, container, false);
        dependencyPresenter = new DependencyAdapterSectionsPresenter(this);
        sectionPresenter = new SectionsFragmentMVPContractPresenter(this);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterdepedencys = new DependecyAdapterSections(new ArrayList<>());
        if (getArguments() != null)
        {
           Section section = (Section) getArguments().get("section");
           binding.txtNombre.setText(section.getName());
           binding.txtShortname.setText(section.getShortname());
           binding.txtDescription.setText(section.getDescription());
           adapterdepedencys.idSelection = section.getIdDepedency();
           adapterdepedencys.update(section.getId());
            binding.button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sectionPresenter.update(new Section(
                            adapterdepedencys.idSelection,
                            binding.txtNombre.getText().toString(),
                            binding.txtShortname.getText().toString(),
                            binding.txtDescription.getText().toString()
                    ));
                }
            });
        }
        else
            {
                binding.button.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sectionPresenter.add(new Section(
                                adapterdepedencys.idSelection,
                                binding.txtNombre.getText().toString(),
                                binding.txtShortname.getText().toString(),
                                binding.txtDescription.getText().toString()
                        ));
                    }
                });
            }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.recyclerView.setAdapter(adapterdepedencys);
        try {
            dependencyPresenter.load();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        binding.ViewNoData.btnAgregar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_sectionsFragment_to_dependencyFragment);
                    }
                }
        );

    }


    @Override
    public void showData(ArrayList list) {
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.ViewNoData.getRoot().setVisibility(View.INVISIBLE);
        adapterdepedencys.update(list);
    }

    @Override
    public void showNoData() {
        binding.recyclerView.setVisibility(View.INVISIBLE);
        binding.ViewNoData.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void add(Section section) {
        sectionPresenter.add(section);
    }

    @Override
    public void update(Section section) {
        sectionPresenter.update(section);
    }

    @Override
    public void onSucess(String msg) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onNameFail() {
        binding.txtNombre.setError(getString(R.string.errorSectionFailName));
    }

    @Override
    public void onShortNameFail() {
        binding.txtShortname.setError(getString(R.string.errorSectionFailShortName));
    }

    @Override
    public void onDescriptionFail() {
        binding.txtDescription.setError(getString(R.string.errorSectionFailDescription));
    }

    @Override
    public void onIdDependencyNoSelect() {
        Toast.makeText(getContext(),R.string.errorSectionIdNoSelected,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNameDuplicate() {
        binding.txtNombre.setError(getString(R.string.errorSectionNameDuplicate));

    }

    @Override
    public void onShortDuplicate() {
        binding.txtShortname.setError(getString(R.string.errorSectionShortNameDuplicate));
    }

    @Override
    public void onValidName() {
        binding.txtNombre.setError(null);
    }

    @Override
    public void onValidShortName() {
        binding.txtShortname.setError(null);
    }

    @Override
    public void onValidDescription() {
        binding.txtDescription.setError(null);
    }
}
