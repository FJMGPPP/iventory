package com.fjmg.inventory.ui.dependency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.inventory.R;
import com.fjmg.inventory.data.model.Dependecy;

import java.util.ArrayList;

public class DependecyAdapter extends RecyclerView.Adapter<DependecyAdapter.ViewHolder> {

    //Para usar la view de clase o la mia personalizada
    Boolean custom = true;
    private ArrayList<Dependecy> list;

    DependecyAdapter()
    {
        this.list = new ArrayList<Dependecy>();
        list.add(new Dependecy("Aula de 1CFGS","1CFGS","Esto es un texto prueba",null));
        list.add(new Dependecy("Aula de 1CFGM","1CFGS","Esto es un texto prueba",null));
        list.add(new Dependecy("Aula de 2CFGS","1CFGS","Esto es un texto prueba",null));
        list.add(new Dependecy("Aula de 2CFGM","1CFGS","Esto es un texto prueba",null));
        list.add(new Dependecy("BigData","1big","Esto es un texto prueba",null));
    }
    @NonNull
    @Override
    public DependecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (custom) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependecy_item_custom, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        }
        return  new ViewHolder(view);
    }

    /**
     * Se llama tanta veces elemento se vizualicen en pantalla / elementos del arrayList del
     * dispostivo movil siempre y cuando getItemCount > 0
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DependecyAdapter.ViewHolder holder, int position)
    {
        holder.tvName.setText(list.get(position).getName());
        if (custom)
        {
            holder.tvShortname.setText(list.get(position).getShortname());
            holder.etmDescription.setText(list.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvShortname;
        EditText etmDescription;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNombre);
            if (custom)
            {
                tvShortname = itemView.findViewById(R.id.tvShortname);
                etmDescription = itemView.findViewById(R.id.etmDescription);
            }
        }
    }
}
