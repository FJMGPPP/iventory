package com.fjmg.inventory.ui.sections;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.fjmg.inventory.R;
import com.fjmg.inventory.base.DependencyOrdenator;
import com.fjmg.inventory.data.model.Dependecy;
import com.fjmg.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> implements SectionsListContract.Adapter {

    public boolean isEmpty;
    //Para usar la view de clase o la mia personalizada
    private ArrayList<Section> list;
    private onManagesectionLister lisener;
    boolean inverse = false;
    SectionAdapter(ArrayList<Section> list, onManagesectionLister  lisener)
    {
        this.list = list;
        this.lisener = lisener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sections_item_custom, parent, false);
        return  new ViewHolder(view);
    }

    /**
     * Se llama tanta veces elemento se vizualicen en pantalla / elementos del arrayList del
     * dispostivo movil siempre y cuando getItemCount > 0
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvName.setText(list.get(position).getName());
        holder.tvShortname.setText(list.get(position).getShortname());
        holder.etmDescription.setText(list.get(position).getDescription());
        holder.tvDependencia.setText(list.get(position).getDependencyName());
        holder.bind(list.get(position),lisener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    @Override
    public void order() {
        if (inverse)
        {
            Collections.sort(list, new DependencyOrdenator());
        }
        else
            {
                Collections.sort(list,Collections.reverseOrder());
            }
        inverse = !inverse;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvShortname;
        EditText etmDescription;
        TextView tvDependencia;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNombre);
            tvShortname = itemView.findViewById(R.id.tvShortname);
            etmDescription = itemView.findViewById(R.id.etmDescription);
            tvDependencia = itemView.findViewById(R.id.tvDependenciName);
        }
        public void bind(Section section, onManagesectionLister listener)
        {
            itemView.findViewById(R.id.Eliminar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnDeleteSection(section);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    listener.OnEditSection(section);
                }
            });
        }
    }
    public void update(ArrayList list) {
        this.list.clear();
        this.list.addAll(list);

        //ERROR EN EL EXAMEN QUE NO ACTUALIZA
        notifyDataSetChanged();
    }
    public void update(Section section)
    {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(section.getName()))
            {
                list.set(i,section);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void delete(Section section) {
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getName().equals(section.getName()))
            {
                list.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void undo(Section section) {
            list.add(section);
            notifyDataSetChanged();
    }

}
