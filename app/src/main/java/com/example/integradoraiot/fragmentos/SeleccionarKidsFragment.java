package com.example.integradoraiot.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.KidsAdapter;
import com.example.integradoraiot.Adapters.SeleccionarKidsAdapter;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import com.example.integradoraiot.ui.activityJuegos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionarKidsFragment extends Fragment implements SeleccionarKidsAdapter.OnKidSelectedListener {

    private RecyclerView recyclerView;
    private SeleccionarKidsAdapter seleccionarKidsAdapter;
    private String gameName;  // Variable para almacenar el gameName

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View view = inflater.inflate(R.layout.activity_seleccionar_kids, container, false);

        // Obtén el Bundle que se pasó desde la actividad
        Bundle arguments = getArguments();
        if (arguments != null) {
            gameName = arguments.getString("gameName");  // Obtén el gameName
            Log.d("GameName", gameName);  // Imprime gameName para depuración
        }

        // Configura el RecyclerView
        recyclerView = view.findViewById(R.id.recycler_children);
        seleccionarKidsAdapter = new SeleccionarKidsAdapter(new ArrayList<>(), this);  // Pasamos el listener
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(seleccionarKidsAdapter);

        // Obtén los datos de los niños
        fetchKidsData();

        return view;
    }

    private void fetchKidsData() {
        TokenManager tokenManager = new TokenManager(getContext());
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);
        String token = "Bearer " + obtenerToken(); // Asegúrate de obtener el token de alguna manera
        ApiService apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);

        Call<ApiResponse> call = apiService.getKids(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        List<modelo_kids> kidsList = apiResponse.getNiños();
                        seleccionarKidsAdapter.setKidsList(kidsList);
                    }
                } else {
                    // Manejo de error
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejo de error
            }
        });
    }

    private String obtenerToken() {
        TokenManager tokenManager = new TokenManager(getContext());
        return tokenManager.getToken();
    }

    // Implementación del método de la interfaz OnKidSelectedListener
    @Override
    public void onKidSelected(modelo_kids kid) {
        // Aquí puedes manejar lo que sucede cuando el niño es seleccionado
        // Por ejemplo, puedes iniciar un nuevo activity y pasar los datos del niño
        Intent intent = new Intent(getActivity(), activityJuegos.class);
        intent.putExtra("kid_name", kid.getNombre());
        intent.putExtra("kid_lastname", kid.getApellido_paterno());
        intent.putExtra("kid_age", kid.getEdad());
        intent.putExtra("id_kid", kid.getId_kid());
        intent.putExtra("gameName", gameName);  // Pasar el gameName al activityJuegos

        startActivity(intent);
    }
}
