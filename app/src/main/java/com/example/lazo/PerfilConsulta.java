package com.example.lazo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilConsulta extends AppCompatActivity {
    private TextView nombrePerfil, categoriaPerfil, telefonoPerfil, direccionPerfil, descripcionPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_consulta);

        // Inicializa las vistas
        nombrePerfil = findViewById(R.id.nombrePerfil);
        categoriaPerfil = findViewById(R.id.categoriaPerfil);
        telefonoPerfil = findViewById(R.id.telefonoPerfil);
        direccionPerfil = findViewById(R.id.direccionPerfil);
        descripcionPerfil = findViewById(R.id.descripcionPerfil);

        // Recibe los datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String categoria = intent.getStringExtra("categoria");
        String telefono = intent.getStringExtra("telefono");
        String direccion = intent.getStringExtra("direccion");
        String descripcion = intent.getStringExtra("descripcion");

        // Asigna los valores a las vistas
        nombrePerfil.setText(nombre);
        categoriaPerfil.setText(categoria);
        telefonoPerfil.setText(telefono);
        direccionPerfil.setText(direccion);
        descripcionPerfil.setText(descripcion);
    }
}

