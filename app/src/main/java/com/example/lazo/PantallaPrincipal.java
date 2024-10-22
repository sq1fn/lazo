package com.example.lazo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tb);

        TabLayout tl = (TabLayout) findViewById(R.id.navbottom);

        TabLayout.Tab initialTab = tl.getTabAt(0);
        if (initialTab != null) {
            initialTab.select();
        }

        //Fragmento seleccionado al iniciar la aplicación
        HomeFragment h = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor, h).commit();

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                switch (position){
                    //tab seleccionado
                    case 0:
                        HomeFragment h = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor,h).commit();
                        break;

                    case 1:
                        BuscarFragment b = new BuscarFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor,b).commit();
                        break;

                    case 2:
                        DonacionFragment d = new DonacionFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor,d).commit();
                        break;

                    case 3:
                        HistorialFragment h2 = new HistorialFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor,h2).commit();
                        break;

                    case 4:
                        PerfilFragment p = new PerfilFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contendedor,p).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab al deseleccionar
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab al reseleccionar
            }
        });

        //incorporar barra lateral
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.contacto){
                    Toast.makeText(getApplicationContext(), "palcontacto", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nosotros){
                    Toast.makeText(getApplicationContext(),"connosotros", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.terminos) {
                    Toast.makeText(getApplicationContext(),"palosterminos",Toast.LENGTH_SHORT).show();
                } else if (id==R.id.logout) {
                    Toast.makeText(getApplicationContext(),"pacerrarsesión",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.drawer_open,
                R.string.drawer_close
        );
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }else{
                    dl.openDrawer((int)Gravity.START);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pprincipal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id == R.id.notificaciones){
            Toast.makeText(this,"son notificaciones",Toast.LENGTH_SHORT).show();
        }return false;
    };
}
