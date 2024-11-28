package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionarKidsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SeleccionarKidsAdapter seleccionarKidsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View view = inflater.inflate(R.layout.activity_seleccionar_kids, container, false);

        recyclerView = view.findViewById(R.id.recycler_children); // Cambia por el ID de tu RecyclerView
        seleccionarKidsAdapter = new SeleccionarKidsAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(seleccionarKidsAdapter);

        // Llamada a la API para obtener los datos
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
}
