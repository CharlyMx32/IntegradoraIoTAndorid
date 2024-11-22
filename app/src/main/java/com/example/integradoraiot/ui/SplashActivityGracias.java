package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.integradoraiot.R;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivityGracias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gracias);

        Button GraciasButton = findViewById(R.id.gracias_button);
        GraciasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivityGracias.this, SplashActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
