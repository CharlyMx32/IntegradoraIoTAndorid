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

import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionarKidsAdapter extends RecyclerView.Adapter<SeleccionarKidsAdapter.KidsViewHolder> {

    private List<modelo_kids> kidsList;
    private OnKidSelectedListener onKidSelectedListener;

    public interface OnKidSelectedListener {
        void onKidSelected(modelo_kids kid);
    }

    public SeleccionarKidsAdapter(List<modelo_kids> kidsList, OnKidSelectedListener onKidSelectedListener) {
        this.kidsList = kidsList;
        this.onKidSelectedListener = onKidSelectedListener;
    }

    @NonNull
    @Override
    public KidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_seleccionar_kids, parent, false);
        return new KidsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsViewHolder holder, int position) {
        modelo_kids modelo_kids = kidsList.get(position);
        holder.nameTextView.setText("Nombre: " + modelo_kids.getNombre());
        holder.lastNameTextView.setText("Apellido: " + modelo_kids.getApellido_paterno());
        holder.ageTextView.setText("Edad: " + modelo_kids.getEdad() + " años");

        holder.selectButton.setOnClickListener(v -> {
            if (onKidSelectedListener != null) {
                onKidSelectedListener.onKidSelected(modelo_kids);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kidsList != null ? kidsList.size() : 0;
    }

    // Método para actualizar la lista de niños
    public void setKidsList(List<modelo_kids> kidsList) {
        this.kidsList = kidsList;
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado y se debe actualizar la vista
    }

    static class KidsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, lastNameTextView, ageTextView;
        Button selectButton;

        public KidsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.child_name);
            lastNameTextView = itemView.findViewById(R.id.child_lastname);
            ageTextView = itemView.findViewById(R.id.child_age);
            selectButton = itemView.findViewById(R.id.btn_select);
        }
    }
}
