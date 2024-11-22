package com.example.integradoraiot.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.ui.SplashActivityItemsDescripciones;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> gameList;

    public GameAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cards_juegos, parent, false);

        return new GameViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.tvGameName.setText(game.getName());
        holder.imgGameIcon.setImageResource(game.getImageResource());

        // Configurar clic en el CardView
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SplashActivityItemsDescripciones.class);
            intent.putExtra("game_name", game.getName());
            intent.putExtra("game_icon", game.getImageResource());
            intent.putExtra("game_description", game.getDescription()); // Agregar la descripción
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameName;
        ImageView imgGameIcon;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGameName = itemView.findViewById(R.id.tvGameName);
            imgGameIcon = itemView.findViewById(R.id.imgGameIcon);
        }
    }
}
