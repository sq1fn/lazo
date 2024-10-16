package com.example.lazo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerfilConsulta extends AppCompatActivity {
    TextView nombrePerfil, categoriaPerfil, direccionPerfil, telefonoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_consulta);

        nombrePerfil = findViewById(R.id.nombrePerfil);
        categoriaPerfil = findViewById(R.id.categoriaPerfil);
        direccionPerfil = findViewById(R.id.direccionPerfil);
        telefonoPerfil = findViewById(R.id.telefonoPerfil);

        // Obtener datos de la intención
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombrePerfil.setText(bundle.getString("Nombre"));
            categoriaPerfil.setText(bundle.getString("Categoria"));
            direccionPerfil.setText(bundle.getString("Direccion"));
            telefonoPerfil.setText(bundle.getString("Telefono"));
        }
    }
}
