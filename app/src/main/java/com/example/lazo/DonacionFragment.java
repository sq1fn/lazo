package com.example.lazo;

import android.annotation.SuppressLint;
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

public class DonacionFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter_D adapter1;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donacion, container, false); //

        tabLayout = view.findViewById(R.id.tab_layout_d);
        viewPager2 = view.findViewById(R.id.view_pager_d);

        tabLayout.addTab(tabLayout.newTab().setText("Seleccion"));
        tabLayout.addTab(tabLayout.newTab().setText("Detalle"));
        tabLayout.addTab(tabLayout.newTab().setText("Agenda"));

        FragmentManager fragmentManager = getChildFragmentManager();
        adapter1 = new ViewPagerAdapter_D(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter1);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Seleccion");
                    break;
                case 1:
                    tab.setText("Detalle");
                    break;
                case 2:
                    tab.setText("Agenda");
                    break;}}).attach();
        return view;
    }
}
