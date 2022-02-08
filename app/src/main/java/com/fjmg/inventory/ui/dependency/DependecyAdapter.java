package com.fjmg.inventory.ui.dependency;

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
import com.google.firebase.components.Dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DependecyAdapter extends RecyclerView.Adapter<DependecyAdapter.ViewHolder> implements DependencyListContract.Adapter {

    public boolean isEmpty;
    //Para usar la view de clase o la mia personalizada
    private ArrayList<Dependecy> list;
    private DependencyListContract.Adapter.onManageDepedencyLister lisener;
    boolean inverse = false;
    DependecyAdapter(ArrayList<Dependecy> list,DependencyListContract.Adapter.onManageDepedencyLister  lisener)
    {
        this.list = list;
        this.lisener = lisener;
    }
    @NonNull
    @Override
    public DependecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependecy_item_custom, parent, false);
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
        holder.tvShortname.setText(list.get(position).getShortname());
        holder.etmDescription.setText(list.get(position).getDescription());
        TextDrawable drawable = TextDrawable.builder()
                    .buildRect(list.get(position).getName().substring(0,1), Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));

        ImageView image = (ImageView)holder.imageView;
        image.setImageDrawable(drawable);
        holder.bind(list.get(position),lisener);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public void delete(Dependecy dependecy) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(dependecy.getName()))
            {
                list.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
        if (list.size() == 0)
        {
            isEmpty = true;
        }
    }

    @Override
    public void undo(Dependecy dependecy) {
        list.add(dependecy);
        order();
        notifyDataSetChanged();
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
        ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNombre);
            tvShortname = itemView.findViewById(R.id.tvShortname);
            etmDescription = itemView.findViewById(R.id.etmDescription);
            imageView = itemView.findViewById(R.id.image_view);
        }
        public void bind(Dependecy dependency, DependencyListContract.Adapter.onManageDepedencyLister listener)
        {
            itemView.findViewById(R.id.Eliminar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnDeleteDepedency(dependency);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    listener.OnEditDepedency(dependency);
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
    public void update(Dependecy dependecy)
    {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(dependecy.getName()))
            {
                list.set(i,dependecy);
                break;
            }
        }
        notifyDataSetChanged();
    }

}
