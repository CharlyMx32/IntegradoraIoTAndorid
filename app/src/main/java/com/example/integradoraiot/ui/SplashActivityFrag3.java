package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;

public class SplashActivityFrag3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.activity_frag3, container, false);

        // Encuentra el botón usando 'view.findViewById'
        Button siguienteButton = view.findViewById(R.id.siguiente_Button);

        siguienteButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();

        });

        return view;
    }
}