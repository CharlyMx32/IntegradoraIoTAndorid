package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.integradoraiot.R;
import com.example.integradoraiot.viewPage.ViewPageAdapter;
import com.example.integradoraiot.viewPage.frag1;
import com.example.integradoraiot.viewPage.frag2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class frag_ventanas extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;
    private int totalPages = 3; // Número total de fragmentos (tabs)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new frag1());
        fragmentList.add(new frag2());
        fragmentList.add(new frag_frag3());

        // Adapter para ViewPager2
        ViewPageAdapter adapter = new ViewPageAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);

        // Configurar TabLayout con ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Aquí puedes personalizar las pestañas si es necesario
        }).attach();

        customizeTabLayout(tabLayout);

        // Método para cambiar las pestañas automáticamente cada cierto tiempo
        startAutoPageChange();

    }


    private void startAutoPageChange() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Cambiar a la siguiente página
                currentPage = (currentPage + 1) % totalPages; // Volver al principio después de la última página
                viewPager.setCurrentItem(currentPage, true); // Cambiar la página
                handler.postDelayed(this, 3000); // 3000 ms = 3 segundos
            }
        };
        handler.postDelayed(runnable, 3000); // Comienza después de 3 segundos
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener el cambio automático de página cuando la actividad se destruye
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void customizeTabLayout(TabLayout tabLayout) {
        // Iterar sobre las vistas internas de las pestañas
        tabLayout.post(() -> {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null) {
                    View tabView = ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(i);
                    if (tabView != null) {
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.setMargins(16, 0, 16, 0); // Ajusta los márgenes como desees
                        tabView.setLayoutParams(params);
                    }
                }
            }
        });

    }
}
