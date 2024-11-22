package com.example.integradoraiot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.integradoraiot.fragmentos.frag_estadisticas;
import com.example.integradoraiot.fragmentos.frag_home;
import com.example.integradoraiot.fragmentos.frag_perfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cambiar a setOnItemSelectedListener (más moderno)
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new frag_home();
                        break;
                    case R.id.nav_estadisticas:
                        selectedFragment = new frag_estadisticas();
                        break;
                    case R.id.nav_perfil:
                        selectedFragment = new frag_perfil();
                        break;
                }

                // Reemplaza el fragmento según el ícono seleccionado
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, selectedFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }

                return true;
            }
        });

        // Establece el fragmento inicial (Home)
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);  // Selecciona el ítem 'Home' por defecto
        }
    }
}
