package com.example.lazo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PerfilConsulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_consulta);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tb);

        String nombre = getIntent().getStringExtra("nombre");
        String categoria = getIntent().getStringExtra("categoria");
        String direccion = getIntent().getStringExtra("direccion");
        String telefono = getIntent().getStringExtra("telefono");

        TextView nombrePerfil = findViewById(R.id.nombrePerfil);
        TextView categoriaPerfil = findViewById(R.id.categoriaPerfil);
        TextView direccionPerfil = findViewById(R.id.direccionPerfil);
        TextView telefonoPerfil = findViewById(R.id.telefonoPerfil);

        nombrePerfil.setText(nombre);
        categoriaPerfil.setText(categoria);
        direccionPerfil.setText(direccion);
        telefonoPerfil.setText(telefono);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil_consulta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.seguir) {
            Toast.makeText(this, "Siguiendo a la fundación", Toast.LENGTH_SHORT).show();
            return true; // Indica que la acción fue manejada
        } else if (id == R.id.reportar) {
            Toast.makeText(this, "Reportando a la fundación", Toast.LENGTH_SHORT).show();
            return true;
        } return false;
    }

}

