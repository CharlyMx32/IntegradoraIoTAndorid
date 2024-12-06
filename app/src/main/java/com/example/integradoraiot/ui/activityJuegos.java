package com.example.integradoraiot.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;

public class activityJuegos extends AppCompatActivity {

    private ConstraintLayout constraintLayout;  // Referencia al layout
    private LottieAnimationView animationView;  // Referencia a la animación Lottie
    private Handler handler;  // Handler para cambiar el color
    private int[] colors = {Color.parseColor("#8636CE"), Color.parseColor("#FF828D"), Color.parseColor("#ADD5D5"), Color.parseColor("#F78B64"), Color.parseColor("#ADD5D5")};  // Colores cíclicos
    private int colorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);

        // Referencias de los componentes
        constraintLayout = findViewById(R.id.activity_juegos);
        animationView = findViewById(R.id.animation_view);

        Intent intent = getIntent();
        int kidId = intent.getIntExtra("id_kid", -1);
        String kidName = intent.getStringExtra("kid_name");
        String kidLastName = intent.getStringExtra("kid_lastname");
        int kidAge = intent.getIntExtra("kid_age", 0);
        String gameName = intent.getStringExtra("gameName");
        animationView.setVisibility(View.VISIBLE);
        handler = new Handler();

        handler.postDelayed(changeColorRunnable, 500);

        enviarDatosAlJuego(gameName, new modelo_kids(kidName, kidLastName, kidAge, gameName));
    }

    private Runnable changeColorRunnable = new Runnable() {
        @Override
        public void run() {
            constraintLayout.setBackgroundColor(colors[colorIndex]);
            colorIndex = (colorIndex + 1) % colors.length;  // Ciclar los colores
            handler.postDelayed(this, 500);  // Ejecutar cada 1000 ms
        }
    };

    private void enviarDatosAlJuego(String gameName, modelo_kids kid) {
        HashMap<String, String> datosJuego = new HashMap<>();
        datosJuego.put("nombre", gameName);
        datosJuego.put("nombre_kid", kid.getNombre());
        TokenManager tokenManager = new TokenManager(this);
        String token = "Bearer " + obtenerToken();
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);
        ApiService apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);

        Call<ApiResponse> call = apiService.enviarDatosJuego(token, datosJuego);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                // Detener el cambio de color y restaurar el color de fondo
                handler.removeCallbacks(changeColorRunnable);
                constraintLayout.setBackgroundColor(Color.WHITE);  // Cambiar a blanco
                animationView.setVisibility(View.GONE);  // Ocultar la animación

                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        actualizarPantallaExito();
                    }
                } else {
                    actualizarPantallaError();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Detener el cambio de color y restaurar el color de fondo
                handler.removeCallbacks(changeColorRunnable);
                constraintLayout.setBackgroundColor(Color.WHITE);  // Cambiar a blanco
                animationView.setVisibility(View.GONE);  // Ocultar la animación
                actualizarPantallaError();
            }
        });
    }

    private void actualizarPantallaExito() {
        // Encontrar el TextView para actualizar el texto
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);

        // Obtener el nombre del juego desde el Intent (si es necesario de nuevo)
        String gameName = getIntent().getStringExtra("gameName");

        // Cambiar el texto a "Jugando: [nombre del juego]"
        if (gameName != null && !gameName.isEmpty()) {
            nameTextView.setText("Jugando: " + gameName);
        } else {
            nameTextView.setText("Jugando");
        }
        nameTextView.setTextSize(24);  // Ajustar el tamaño si es necesario
        nameTextView.setTextColor(Color.BLACK);  // Color del texto

        // Centrar el TextView en la pantalla
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) nameTextView.getLayoutParams();
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        nameTextView.setLayoutParams(params);

        // Mostrar el TextView centrado
        nameTextView.setVisibility(View.VISIBLE);
    }


    private void actualizarPantallaError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


    private String obtenerToken() {
        TokenManager tokenManager = new TokenManager(this);
        return tokenManager.getToken();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener cualquier cambio de color cuando se destruye la actividad
        handler.removeCallbacks(changeColorRunnable);
    }
}
