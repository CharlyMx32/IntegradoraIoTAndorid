package com.example.integradoraiot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.R;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidsViewHolder> {

    private List<modelo_kids> kidsList;

    public KidsAdapter(List<modelo_kids> kidsList) {
        this.kidsList = kidsList;
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

        // Puedes configurar la imagen aquí si tienes una
        // holder.profileImage.setImageResource(R.drawable.ic_some_image);
    }

    @Override
    public int getItemCount() {
        return kidsList != null ? kidsList.size() : 0;
    }

    static class KidsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, lastNameTextView, ageTextView;
        ImageView profileImage;

        public KidsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.child_name);
            lastNameTextView = itemView.findViewById(R.id.child_lastname);
            ageTextView = itemView.findViewById(R.id.child_age);
            profileImage = itemView.findViewById(R.id.img_child);
        }
    }
}
