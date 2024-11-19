package com.example.integradoraiot.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.integradoraiot.R;
import com.example.integradoraiot.viewPage.ViewPageAdapter;
import com.example.integradoraiot.viewPage.frag1;
import com.example.integradoraiot.viewPage.frag2;
import com.example.integradoraiot.viewPage.frag3;
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
        }).attach();

    }
}
