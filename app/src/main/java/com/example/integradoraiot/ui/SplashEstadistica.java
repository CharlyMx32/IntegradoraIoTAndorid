package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;
import com.example.integradoraiot.models.KidRequest2;
import com.example.integradoraiot.models.NewResponse;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashEstadistica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_kids);

        String kidDataJson = getIntent().getStringExtra("kid_data");

        if (kidDataJson != null) {
            try {
                JSONObject kidJson = new JSONObject(kidDataJson);

                String nombreKid = kidJson.getString("nombre_kid");
                String apellidoKid = kidJson.getString("apellido_paterno_kid");

                TextView estadisticasTextViewnombre = findViewById(R.id.child_name);
                TextView estadisticasTextViewapellido = findViewById(R.id.child_lastname);
                TextView estadisticasDetalle = findViewById(R.id.child_estadisticas);

                estadisticasTextViewnombre.setText("Nombre: " + nombreKid);
                estadisticasTextViewapellido.setText("Apellido: " + apellidoKid);

                KidRequest2 request = new KidRequest2(nombreKid, apellidoKid);

                Retrofit retrofit = RetroFitClient.getClientSinToken();
                ApiService apiService = retrofit.create(ApiService.class);

                apiService.getGenerales(request).enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            NewResponse newResponse = response.body();
                            StringBuilder estadisticas = new StringBuilder();

                            if (newResponse.getEstadisticas() != null && !newResponse.getEstadisticas().isEmpty()) {
                                for (NewResponse.Estadistica estadistica : newResponse.getEstadisticas()) {
                                    if (estadistica.getNombre_juego() != null && estadistica.getTotal_tiempo_jugado() != null) {
                                        estadisticas.append(estadistica.getNombre_juego())
                                                .append(": ")
                                                .append(estadistica.getNumero_partidas())
                                                .append(" partidas, ")
                                                .append(estadistica.getTotal_tiempo_jugado())
                                                .append(" jugados\n");
                                    }
                                }
                            } else {
                                estadisticas.append("No hay estadísticas disponibles.");
                            }
                            estadisticasDetalle.setText(estadisticas.toString());
                        } else {
                            Toast.makeText(SplashEstadistica.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(SplashEstadistica.this, "Error al conectar con el servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}