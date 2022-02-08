package com.fjmg.inventory.ui.sections.DependencyAdapterMVP;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.fjmg.inventory.R;
import com.fjmg.inventory.data.model.Dependecy;


import java.util.ArrayList;
import java.util.Random;


public class DependecyAdapterSections extends RecyclerView.Adapter<DependecyAdapterSections.ViewHolder> implements DependencyAdapterSectionsContract.Adapter
{
    ArrayList<Dependecy> list;
    String dependencyNameSelection = null;
    int positionSelection;
    public int idSelection = -1;

    public DependecyAdapterSections(ArrayList<Dependecy> dependecies)
    {
        list = dependecies;
    }

    @NonNull
    @Override
    public DependecyAdapterSections.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dependency_sections,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DependecyAdapterSections.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!holder.contieneIcono)
        {
            holder.dependencyName.setText(list.get(position).getName());
            TextDrawable drawable = TextDrawable.builder()
                    .buildRect(list.get(position).getName().substring(0,1), Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            holder.logo.setImageDrawable(drawable);
            holder.contieneIcono = true;
        }

        if (!holder.dependencyName.getText().equals(dependencyNameSelection) && list.get(position).getId() != idSelection)
        {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.red_cool));
        }
        else
        {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.greenLike));
        }
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dependencyNameSelection = list.get(position).getName();
                        positionSelection = position;
                        idSelection = list.get(position).getId();
                        notifyDataSetChanged();
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void update(ArrayList list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView dependencyName;
        boolean contieneIcono;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            logo  = itemView.findViewById(R.id.ImgDpIcon);
            dependencyName =  itemView.findViewById(R.id.lblNameItemDepend);
            contieneIcono = false;
        }
    }
    public void update(int id)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getId() == id)
            {
                idSelection = id;
                String dependencyNameSelection = "";
                int positionSelection = i;
                break;
            }
        }
        notifyDataSetChanged();
    }
}
