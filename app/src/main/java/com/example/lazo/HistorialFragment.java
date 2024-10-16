package com.example.lazo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistorialFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false); // Cambia a tu layout XML

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager2 = view.findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Pendientes"));
        tabLayout.addTab(tabLayout.newTab().setText("Completadas"));

        FragmentManager fragmentManager = getChildFragmentManager();
        adapter = new ViewPagerAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(position == 0 ? "Pendientes" : "Completadas");
        }).attach();

        return view;
    }
}
