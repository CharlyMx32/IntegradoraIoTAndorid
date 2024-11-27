package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.R;
import com.example.integradoraiot.Adapters.KidsAdapter;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.modelo_kids;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KidsFragment extends Fragment {

    private RecyclerView recyclerView;
    private KidsAdapter kidsAdapter;
    private TextView tutorNameTextView;
    private TextView tutorRoleTextView;
    private ImageView tutorImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View view = inflater.inflate(R.layout.activity_familia, container, false);

        // Inicializamos el RecyclerView
        recyclerView = view.findViewById(R.id.recycler_children);
        kidsAdapter = new KidsAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(kidsAdapter);

        tutorNameTextView = view.findViewById(R.id.tutor_name);
        tutorRoleTextView = view.findViewById(R.id.tutor_role);
        tutorImageView = view.findViewById(R.id.img_tutor);

        // Aquí se haría la llamada a la API para obtener los datos
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
                        tutorNameTextView.setText(apiResponse.getTutor().getNombre());
                        tutorRoleTextView.setText("Tutor principal");

                        if (apiResponse.getTutor().getFotoPerfil() != null) {
                            Picasso.get()
                                    .load(apiResponse.getTutor().getFotoPerfil())
                                    .placeholder(R.drawable.ic_perfil)
                                    .into(tutorImageView);
                        }
                        else {
                            tutorImageView.setImageResource(R.drawable.ic_perfil);
                        }

                        List<modelo_kids> kidsList = apiResponse.getNiños();
                        kidsAdapter.setKidsList(kidsList);
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
        TokenManager tokenManager = new TokenManager(getContext());  // Usando tu clase TokenManager
        return tokenManager.getToken();  // Recupera el token almacenado
    }

}
