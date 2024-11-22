package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

public class DescripcionActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_descripciones); // Asegúrate de tener este layout

        titulo = findViewById(R.id.textTitulo);
        descripcion = findViewById(R.id.textDescripcion);

        // Obtener el ID de la descripción desde el Intent
        Intent intent = getIntent();
        int descripcionId = intent.getIntExtra("descripcionId", -1); // Obtén el ID (valor predeterminado -1)

        // Aquí puedes cargar los datos con el ID recibido (por ejemplo, desde una base de datos o un ViewModel)
        // Suponiendo que tienes un método que obtiene la descripción por ID
        cargarDescripcion(descripcionId);
    }

    private void cargarDescripcion(int id) {
        // Aquí puedes cargar los detalles con el ID recibido, por ejemplo, desde un ViewModel o una base de datos
        if (id != -1) {
            // Simulamos la carga de datos
            titulo.setText("Título de la descripción " + id);
            descripcion.setText("Detalles de la descripción " + id);
        }
    }
}
