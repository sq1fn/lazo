package com.example.lazo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroCuenta extends AppCompatActivity {

    private LinearLayout fundacionExtraFields;
    private EditText nombre, correo, contrasena, direccion;
    private Spinner categoria;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_cuenta);

        // Ajustar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        nombre = findViewById(R.id.registro_nombre);
        correo = findViewById(R.id.registro_correo);
        contrasena = findViewById(R.id.registro_contrasenha);
        direccion = findViewById(R.id.registro_direccion);
        categoria = findViewById(R.id.spinner_categoria);
        Button botonRegistro = findViewById(R.id.boton_registro);
        switchTipoUsuario = findViewById(R.id.switch_tipo_usuario);
        fundacionExtraFields = findViewById(R.id.fundacion_extra_fields);

        // Configurar el switch
        switchTipoUsuario.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fundacionExtraFields.setVisibility(View.VISIBLE);
            } else {
                fundacionExtraFields.setVisibility(View.GONE);
            }
        });

        // Acción del botón de registro
        botonRegistro.setOnClickListener(v -> {
            String nombreUsuario = nombre.getText().toString();
            String correoUsuario = correo.getText().toString();
            String contrasenaUsuario = contrasena.getText().toString();
            boolean esFundacion = switchTipoUsuario.isChecked();

            // Validación básica
            if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || contrasenaUsuario.isEmpty()) {
                Toast.makeText(RegistroCuenta.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Guardar en SharedPreferences
            SharedPreferences preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("nombre", nombreUsuario);
            editor.putString("correo", correoUsuario);
            editor.putString("contrasena", contrasenaUsuario);
            editor.putBoolean("esFundacion", esFundacion);

            if (esFundacion) {
                String direccionUsuario = direccion.getText().toString();
                String categoriaSeleccionada = categoria.getSelectedItem().toString();
                editor.putString("direccion", direccionUsuario);
                editor.putString("categoria", categoriaSeleccionada);
            }

            editor.apply();

            Toast.makeText(RegistroCuenta.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            // Redirigir al inicio de sesión o a otra actividad
            Intent intent = new Intent(RegistroCuenta.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
