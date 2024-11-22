package com.example.integradoraiot.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.ui.DescripcionActivity; // Importa tu actividad de descripción

public class DescripcionAdapter extends ListAdapter<Descripcion, DescripcionAdapter.DescripcionViewHolder> {

    private final Context context;

    public DescripcionAdapter(Context context) {
        super(new DiffUtil.ItemCallback<Descripcion>() {
            @Override
            public boolean areItemsTheSame(@NonNull Descripcion oldItem, @NonNull Descripcion newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Descripcion oldItem, @NonNull Descripcion newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.context = context;
    }

    @NonNull
    @Override
    public DescripcionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_descripciones, parent, false);
        return new DescripcionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DescripcionViewHolder holder, int position) {
        Descripcion current = getItem(position);
        holder.bind(current);
    }

    static class DescripcionViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo;
        private final TextView descripcion;

        public DescripcionViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            descripcion = itemView.findViewById(R.id.textDescripcion);
        }

        public void bind(Descripcion descripcion) {
            titulo.setText(descripcion.getTitulo());
            this.descripcion.setText(descripcion.getDescripcion());

            // Al hacer clic en el ítem, se inicia la nueva actividad
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DescripcionActivity.class);
                // Puedes pasar datos a la actividad de descripción
                intent.putExtra("descripcionId", descripcion.getId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
