package com.example.integradoraiot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.integradoraiot.fragmentos.KidsFragment;
import com.example.integradoraiot.fragmentos.frag_home;
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
                    case R.id.nav_familia:
                        selectedFragment = new KidsFragment();
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Acción de retroceso bloqueada temporalmente", Toast.LENGTH_SHORT).show();
        //no se llama a super.onBackPressed() para bloquear la acción de retroceso
    }
}
