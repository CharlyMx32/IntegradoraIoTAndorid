package com.example.integradoraiot.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

public class activityJuegos extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private LottieAnimationView animationView;
    private Handler handler;
    private int[] colors = {Color.parseColor("#8636CE"), Color.parseColor("#FF828D"),
            Color.parseColor("#ADD5D5"), Color.parseColor("#F78B64"), Color.parseColor("#ADD5D5")};
    private int colorIndex = 0;
    private boolean isInGame = true;
    private int backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);

        constraintLayout = findViewById(R.id.activity_juegos);
        animationView = findViewById(R.id.animation_view);
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);

        animationView.setVisibility(View.VISIBLE);
        nameTextView.setVisibility(View.GONE);
        handler = new Handler();
        handler.postDelayed(changeColorRunnable, 500);

        Intent intent = getIntent();
        int kidId = intent.getIntExtra("id_kid", -1);
        String kidName = intent.getStringExtra("kid_name");
        String kidLastName = intent.getStringExtra("kid_lastname");
        int kidAge = intent.getIntExtra("kid_age", 0);
        String gameName = intent.getStringExtra("gameName");

        modelo_kids kid = new modelo_kids(kidName, kidLastName, kidAge, gameName);
        enviarDatosAlJuego(gameName, kid);
    }

    private Runnable changeColorRunnable = new Runnable() {
        @Override
        public void run() {
            constraintLayout.setBackgroundColor(colors[colorIndex]);
            colorIndex = (colorIndex + 1) % colors.length;
            handler.postDelayed(this, 500);
        }
    };

    private void enviarDatosAlJuego(String gameName, modelo_kids kid) {
        HashMap<String, String> datosJuego = new HashMap<>();
        datosJuego.put("nombre", gameName);
        datosJuego.put("nombre_kid", kid.getNombre());

        String token = "Bearer " + obtenerToken();
        ApiService apiService = RetroFitClient
                .getClient(new TokenInterceptor(new TokenManager(this)))
                .create(ApiService.class);

        Call<ApiResponse> call = apiService.enviarDatosJuego(token, datosJuego);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                handler.removeCallbacks(changeColorRunnable);
                constraintLayout.setBackgroundColor(Color.WHITE);
                animationView.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    handler.removeCallbacks(changeColorRunnable);
                    ApiResponse apiResponse = response.body();
                    String resultado = apiResponse.getResultado();

                    if ("partida iniciada correctamente".equalsIgnoreCase(resultado)) {
                        actualizarPantallaExito(gameName);
                        esperarResultadoDelJuego(kid.getNombre());
                    } else if ("partida finalizada".equalsIgnoreCase(resultado)) {
                        actualizarPantallaVictoria();
                    }
                } else {
                    mostrarError("Error al iniciar partida. Intenta de nuevo.");
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mostrarError("Fallo en la conexión. Revisa tu red.");
            }
        });
    }

    private void esperarResultadoDelJuego(String kidName) {
        handler.postDelayed(() -> verificarResultadoDelJuego(kidName), 180000);
    }

    private void verificarResultadoDelJuego(String kidName) {
        String token = "Bearer " + obtenerToken();
        ApiService apiService = RetroFitClient
                .getClient(new TokenInterceptor(new TokenManager(this)))
                .create(ApiService.class);

        Call<ApiResponse> call = apiService.obtenerResultado(token, kidName);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    String resultado = apiResponse.getResultado();
                    if ("partida en curso".equalsIgnoreCase(resultado)) {
                        esperarResultadoDelJuego(kidName); // Seguir esperando
                    } else if ("partida finalizada".equalsIgnoreCase(resultado)) {
                        actualizarPantallaVictoria();
                    }
                } else {
                    mostrarError("Error al obtener resultado. Intenta de nuevo.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mostrarError("Fallo en la conexión. Revisa tu red.");
            }
        });
    }

    private void actualizarPantallaExito(String gameName) {
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);
        nameTextView.setText("Partida en curso: " + gameName);
        nameTextView.setTextColor(Color.BLACK);
        nameTextView.setVisibility(View.VISIBLE);
    }

    private void actualizarPantallaVictoria() {
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);
        nameTextView.setText("¡Has ganado!");
        nameTextView.setTextColor(Color.GREEN);
        nameTextView.setVisibility(View.VISIBLE);
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        finish();
    }

    private String obtenerToken() {
        return new TokenManager(this).getToken();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(changeColorRunnable);
    }

    @Override
    public void onBackPressed() {
        if (isInGame) {
            backPressCount++;
            if (backPressCount == 1) {
                Toast.makeText(this, "Presiona atrás nuevamente para salir del juego", Toast.LENGTH_SHORT).show();
            } else if (backPressCount == 2) {
                new AlertDialog.Builder(this)
                        .setMessage("¿Estás seguro de que quieres salir del juego?")
                        .setPositiveButton("Sí", (dialog, id) -> finish())
                        .setNegativeButton("No", null)
                        .show();
            }
        } else {
            super.onBackPressed();
        }
    }
}
