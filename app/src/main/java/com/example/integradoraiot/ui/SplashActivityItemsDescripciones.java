package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.integradoraiot.R;
import com.example.integradoraiot.fragmentos.KidsFragment;
import com.example.integradoraiot.fragmentos.SeleccionarKidsFragment;
import com.squareup.picasso.Picasso;

public class SplashActivityItemsDescripciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones);

        TextView gameName = findViewById(R.id.tvGameNameDetail);
        TextView gameDescription = findViewById(R.id.tvGameDescription);
        Intent intent = getIntent();
        String name = getIntent().getStringExtra("gameName");
        String description = getIntent().getStringExtra("gameDescription");
        String gameId = intent.getStringExtra("gameId"); // Obtén el ID

        gameName.setText(name);
        gameDescription.setText(description);

        Button btnPlayGame = findViewById(R.id.btnPlayGame);
        CardView cardFragmentContainer = findViewById(R.id.card_fragment_container);

        btnPlayGame.setOnClickListener(v -> {
            cardFragmentContainer.setVisibility(View.VISIBLE);

            // Crea un nuevo Bundle para pasar el gameName
            Bundle bundle = new Bundle();
            bundle.putString("gameName", name); // Pasa el gameName

            // Configura el fragmento y pasa el Bundle
            SeleccionarKidsFragment seleccionarKidsFragment = new SeleccionarKidsFragment();
            seleccionarKidsFragment.setArguments(bundle); // Asigna el Bundle al fragmento

            // Realiza la transacción del fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, seleccionarKidsFragment)
                    .commit();
        });

    }
}
