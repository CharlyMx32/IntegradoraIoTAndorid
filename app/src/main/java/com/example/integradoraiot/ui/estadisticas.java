package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        // Inicializa vistas
        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnShowAll = findViewById(R.id.btnShowAll);
        RecyclerView recyclerViewStats = findViewById(R.id.recyclerViewStats);

        // Configura el MaterialDatePicker
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona una fecha")
                .build();

        btnDatePicker.setOnClickListener(v -> {
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });

        datePicker.addOnPositiveButtonClickListener(selection -> {
            // Maneja la fecha seleccionada
            String selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(selection));
            fetchStatsByDate(selectedDate, recyclerViewStats);
        });

        btnShowAll.setOnClickListener(v -> {
            fetchAllStats(recyclerViewStats);
        });
    }

    private void fetchStatsByDate(String date, RecyclerView recyclerView) {
    }

    private void fetchAllStats(RecyclerView recyclerView) {

    }
}
