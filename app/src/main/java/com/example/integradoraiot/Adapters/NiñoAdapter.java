package com.example.integradoraiot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.integradoraiot.models.Niño;
import com.example.integradoraiot.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NiñoAdapter extends RecyclerView.Adapter<NiñoAdapter.NinoViewHolder> {

    private List<Niño> listaNinos;
    private OnNinoClickListener listener;

    public NiñoAdapter(List<Niño> listaNinos, OnNinoClickListener listener) {
        this.listaNinos = listaNinos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nino, parent, false);
        return new NinoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NinoViewHolder holder, int position) {
        Niño nino = listaNinos.get(position);
        holder.tvNombre.setText(nino.getNombre());
        holder.tvApellido.setText(nino.getApellido());
        holder.itemView.setOnClickListener(v -> listener.onNinoClick(nino));
    }

    @Override
    public int getItemCount() {
        return listaNinos.size();
    }

    public static class NinoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvApellido;

        public NinoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
        }
    }

    public interface OnNinoClickListener {
        void onNinoClick(Niño niño);
    }
}
