package com.example.integradoraiot.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class SplashActivityVentanas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new frag1());
        fragmentList.add(new frag2());
        fragmentList.add(new SplashActivityFrag3());

        ViewPageAdapter adapter = new ViewPageAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Este método es donde podemos hacer ajustes adicionales
        }).attach();

        customizeTabLayout(tabLayout);
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

    //esto es para bloquear la acción de retroceso
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Acción de retroceso bloqueada temporalmente", Toast.LENGTH_SHORT).show();
        //no se llama a super.onBackPressed() para bloquear la acción de retroceso
    }

}