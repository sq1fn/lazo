package com.example.lazo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter_D extends FragmentStateAdapter {

    public ViewPagerAdapter_D(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new dDetalleProducto();
            case 2:
                return new dAgendar();
            default:
                return new dSeleccionProductos();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}