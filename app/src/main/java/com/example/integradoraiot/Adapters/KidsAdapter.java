package com.example.integradoraiot.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.R;
import com.example.integradoraiot.ui.SplashEstadistica;


import org.json.JSONObject;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidsViewHolder> {

    private List<modelo_kids> kidsList;

    public KidsAdapter(List<modelo_kids> kidsList) {
        this.kidsList = kidsList;
    }

    public void setKidsList(List<modelo_kids> kidsList) {
        this.kidsList = kidsList;
        notifyDataSetChanged();  // Notifica al RecyclerView que la lista ha cambiado
    }

    @NonNull
    @Override
    public KidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_kids, parent, false);
        return new KidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsViewHolder holder, int position) {
        modelo_kids modelo_kids = kidsList.get(position);
        holder.nameTextView.setText("Nombre: " + modelo_kids.getNombre());
        holder.lastNameTextView.setText("Apellido: " + modelo_kids.getApellido_paterno());
        holder.ageTextView.setText("Edad: " + modelo_kids.getEdad() + " años");

        holder.btnVer.setOnClickListener(v -> {
            try {
                // Crear el objeto JSON con los datos del niño
                JSONObject kidJson = new JSONObject();
                kidJson.put("nombre_kid", modelo_kids.getNombre());
                kidJson.put("apellido_paterno_kid", modelo_kids.getApellido_paterno());

                // Convertir el objeto JSON a cadena y enviarlo en el Intent
                Intent intent = new Intent(holder.itemView.getContext(), SplashEstadistica.class);
                intent.putExtra("kid_data", kidJson.toString());  // Pasa el JSON como cadena
                holder.itemView.getContext().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public int getItemCount() {
        return kidsList != null ? kidsList.size() : 0;
    }

    static class KidsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, lastNameTextView, ageTextView;
        ImageView profileImage;
        Button btnVer;

        public KidsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.child_name);
            lastNameTextView = itemView.findViewById(R.id.child_lastname);
            ageTextView = itemView.findViewById(R.id.child_age);
            profileImage = itemView.findViewById(R.id.img_child);
            btnVer = itemView.findViewById(R.id.btnVer);
        }
    }
}