package com.example.lazo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class PerfilConsulta extends AppCompatActivity {
    private TextView nombrePerfil, categoriaPerfil, telefonoPerfil, direccionPerfil, descripcionPerfil;
    private ImageView fotoPerfil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_consulta);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tb);

        fotoPerfil = findViewById(R.id.fotoperfil);
        nombrePerfil = findViewById(R.id.nombrePerfil);
        categoriaPerfil = findViewById(R.id.categoriaPerfil);
        telefonoPerfil = findViewById(R.id.telefonoPerfil);
        direccionPerfil = findViewById(R.id.direccionPerfil);
        descripcionPerfil = findViewById(R.id.descripcionPerfil);


        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String categoria = intent.getStringExtra("categoria");
        String telefono = intent.getStringExtra("telefono");
        String direccion = intent.getStringExtra("direccion");
        String descripcion = intent.getStringExtra("descripcion");
        String fotoUrl = intent.getStringExtra("fotoPerfil");

        nombrePerfil.setText(nombre);
        categoriaPerfil.setText(categoria);
        telefonoPerfil.setText(telefono);
        direccionPerfil.setText(direccion);
        descripcionPerfil.setText(descripcion);

        Glide.with(this)
                .load(fotoUrl)
                .into(fotoPerfil);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil_consulta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notificaciones) {
            Toast.makeText(this, "Son notificaciones", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

