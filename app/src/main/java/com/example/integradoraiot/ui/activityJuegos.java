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
import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.Adafruit;
import com.example.integradoraiot.models.estadisticas;
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
    private String kidName;
    private boolean isGameActive = true;
    private String gameName;

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
        kidName = intent.getStringExtra("kid_name");
        String kidLastName = intent.getStringExtra("kid_lastname");
        int kidAge = intent.getIntExtra("kid_age", 0);
        gameName = intent.getStringExtra("gameName");

        modelo_kids kid = new modelo_kids(kidName, kidLastName, kidAge, gameName);
        enviarDatosAlJuego(gameName, kid);

        handler.postDelayed(stateRunnable, 8000);
    }

    private Runnable stateRunnable = new Runnable() {
        @Override
        public void run() {
            verificarEstadoPartida();
            handler.postDelayed(this, 8000);
        }
    };

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
        ApiService apiService;

        if ("Luz verde luz roja".equals(gameName)) {

            apiService = RetroFitClient.getClient(new TokenInterceptor(new TokenManager(this)))
                    .create(ApiService.class);
            Call<ApiResponse> call = apiService.enviarDatosJuegoDos(token, datosJuego);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        esperarResultadoDelJuego(kid.getNombre());
                    } else {
                        mostrarError("Error al iniciar partida. Intenta de nuevo.");
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    mostrarError("Fallo en la conexión. Revisa tu red.");
                }
            });
        } else {
            // Usar endpoints del primer juego
            apiService = RetroFitClient.getClient(new TokenInterceptor(new TokenManager(this)))
                    .create(ApiService.class);
            Call<ApiResponse> call = apiService.enviarDatosJuego(token, datosJuego);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        esperarResultadoDelJuego(kid.getNombre());
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
    }

    private void esperarResultadoDelJuego(String kidName) {
        handler.postDelayed(() -> verificarEstadoPartida(), 1000);
    }

    private void verificarEstadoPartida() {
        ApiService apiService;

        if ("Luz verde luz roja".equals(gameName)) {
            // Usar endpoints del segundo juego
            apiService = RetroFitClient.getAdafruitClient().create(ApiService.class);
            Call<Adafruit> call = apiService.getFeedStatusDos();
            call.enqueue(new Callback<Adafruit>() {
                @Override
                public void onResponse(Call<Adafruit> call, Response<Adafruit> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String lastValue = response.body().getLastValue();
                        if ("1".equals(lastValue)) {
                            iniciarPartida();
                        } else if ("0".equals(lastValue)) {
                            terminarPartida();
                            isGameActive = false;
                        }
                    } else {
                        mostrarError("Error al obtener el estado del juego");
                    }
                }

                @Override
                public void onFailure(Call<Adafruit> call, Throwable t) {
                    mostrarError("Fallo en la conexión. Revisa tu red.");
                }
            });
        } else {
            // Usar endpoints del primer juego
            apiService = RetroFitClient.getAdafruitClient().create(ApiService.class);
            Call<Adafruit> call = apiService.getFeedStatus();
            call.enqueue(new Callback<Adafruit>() {
                @Override
                public void onResponse(Call<Adafruit> call, Response<Adafruit> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String lastValue = response.body().getLastValue();
                        if ("1".equals(lastValue)) {
                            iniciarPartida();
                        } else if ("0".equals(lastValue)) {
                            terminarPartida();
                            isGameActive = false;
                        }
                    } else {
                        mostrarError("Error al obtener el estado del juego");
                    }
                }

                @Override
                public void onFailure(Call<Adafruit> call, Throwable t) {
                    mostrarError("Fallo en la conexión. Revisa tu red.");
                }
            });
        }
    }

    private void obtenerEstadisticas() {
        ApiService apiService = RetroFitClient
                .getClient(new TokenInterceptor(new TokenManager(this)))
                .create(ApiService.class);

        Call<ApiResponse> call = apiService.obtenerEstadisticas();  // Endpoint para obtener estadísticas
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    estadisticas stats = apiResponse.getEstadisticas();

                    Intent intent = new Intent(activityJuegos.this, activityestadisticas.class);
                    intent.putExtra("estadisticas",  stats);  // Pasar el objeto estadisticas
                    startActivity(intent);
                } else {
                    mostrarError("Error al obtener las estadísticas");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mostrarError("Fallo en la conexión. Revisa tu red.");
            }
        });
    }

    private void obtenerEstadisticasDos() {
        ApiService apiService = RetroFitClient
                .getClient(new TokenInterceptor(new TokenManager(this)))
                .create(ApiService.class);

        Call<ApiResponse> call = apiService.obtenerEstadisticasDos();  // Endpoint para obtener estadísticas
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    estadisticas stats = apiResponse.getEstadisticas();

                    Intent intent = new Intent(activityJuegos.this, activityestadisticas.class);
                    intent.putExtra("estadisticas",  stats);
                    startActivity(intent);
                } else {
                    mostrarError("Error al obtener las estadísticas");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mostrarError("Fallo en la conexión. Revisa tu red.");
            }
        });
    }

    private void iniciarPartida() {
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);
        nameTextView.setText("Jugando");
        nameTextView.setTextColor(Color.BLACK);
        nameTextView.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.GONE);
    }

    private void terminarPartida() {
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);
        isGameActive = false;

        if ("Luz verde luz roja".equals(gameName)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Partida terminada")
                    .setMessage("¿Deseas ver tus estadísticas?")
                    .setPositiveButton("Sí", (dialog, which) -> obtenerEstadisticasDos())
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                        Intent intent = new Intent(activityJuegos.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setCancelable(false)
                    .show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Partida terminada")
                    .setMessage("¿Deseas ver tus estadísticas?")
                    .setPositiveButton("Sí", (dialog, which) -> obtenerEstadisticas())
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                        Intent intent = new Intent(activityJuegos.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setCancelable(false)
                    .show();
        }
        obtenerEstadisticas();
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private String obtenerToken() {
        TokenManager tokenManager = new TokenManager(this);
        return tokenManager.getToken();
    }
}
