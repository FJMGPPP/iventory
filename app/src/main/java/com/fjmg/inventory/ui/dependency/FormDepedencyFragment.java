package com.fjmg.inventory.ui.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.fjmg.inventory.R;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.databinding.FragmentFormDependencyBinding;

public class FormDepedencyFragment extends Fragment implements FormDepedencyContract.View
{
    FragmentFormDependencyBinding binding;
    FormDepedencyContract.Presenter presenter;
    boolean update = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentFormDependencyBinding.inflate(inflater, container, false);
        presenter = new FormDepedencyPresenter(this);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
        {
            update = true;
            Dependecy tmp = (Dependecy) getArguments().get("Dependency");
            binding.txtDescripcionForm.setText(tmp.getDescription());
            binding.txtNombreForm.setText(tmp.getName());
            binding.txtShortnameForm.setText(tmp.getShortname());
            binding.buttonFin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tmp.setDescription( binding.txtDescripcionForm.getText().toString());
                    tmp.setName(binding.txtNombreForm.getText().toString());
                    tmp.setShortname(binding.txtShortnameForm.getText().toString());
                    presenter.update(tmp);
                }
            });
        }
        else
            {
                update = false;
                binding.buttonFin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.add(new Dependecy(
                                binding.txtNombreForm.getText().toString(),
                                binding.txtShortnameForm.getText().toString(),
                                binding.txtDescripcionForm.getText().toString(),
                                null
                                ));
                    }
                });
            }
    }

    @Override
    public void onSucess(String msg) {
        Navigation.findNavController(this.getView()).navigateUp();
    }

    @Override
    public void onFail(String msg)
    {

    }

    @Override
    public void onNameFail() {
        binding.txtNombreForm.setError(getString(R.string.errorDependencyFailName));
        binding.txtNombreForm.setText("");
    }

    @Override
    public void onShortNameFail() {
        binding.txtShortnameForm.setError(getString(R.string.errorDependencyFailShortName));
        binding.txtShortnameForm.setText("");
    }

    @Override
    public void onShortNameDuplicate()
    {
        binding.txtShortnameForm.setError(getString(R.string.errorDependencyShortNameDuplicateName));
        binding.txtShortnameForm.setText("");
    }

    @Override
    public void onNameDuplicate() {
        binding.txtNombreForm.setError(getString(R.string.errorDependencyDuplicateName));
        binding.txtNombreForm.setText("");
    }

    @Override
    public void onDescripcionInvalid() {
        binding.txtDescripcionForm.setError(getString(R.string.errorDependencyFailDescription));
        binding.txtDescripcionForm.setText("");
    }

    @Override
    public void onNameValid() {
        binding.txtDescripcionForm.setError(null);
    }

    @Override
    public void onShortNameValid() {
        binding.txtDescripcionForm.setError(null);
    }

    @Override
    public void onDescriptionValid() {
        binding.txtDescripcionForm.setError(null);
    }
}

