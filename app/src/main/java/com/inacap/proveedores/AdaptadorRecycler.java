package com.inacap.proveedores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos> {


    ArrayList<String> listProductos;
    public AdaptadorRecycler(ArrayList<String> listProductos){
        this.listProductos = listProductos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedidos,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listProductos.get(position));
    }

    @Override
    public int getItemCount() {
        return listProductos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView producto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            producto= itemView.findViewById(R.id.id_tv1);
        }

        public void asignarDatos(String s) {
            producto.setText(s);
        }
    }
}
