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
    private EditText nombre, correo, contrasena, direccion, telefono;
    private Spinner categoria;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTipoUsuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_cuenta);

        //Ajustar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializar vistas
        nombre = findViewById(R.id.registro_nombre);
        correo = findViewById(R.id.registro_correo);
        contrasena = findViewById(R.id.registro_contrasenha);
        direccion = findViewById(R.id.registro_direccion);
        categoria = findViewById(R.id.spinner_categoria);
        telefono = findViewById(R.id.registro_telefono);
        Button botonRegistro = findViewById(R.id.boton_registro);
        switchTipoUsuario = findViewById(R.id.switch_tipo_usuario);
        fundacionExtraFields = findViewById(R.id.fundacion_extra_fields);

        //Activar funcion del switch
        // el cual muestra campos relacionados a las fundaciones
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
            String telefonoUsuario = telefono.getText().toString();
            boolean esFundacion = switchTipoUsuario.isChecked();

            // Validación de nombre
            if (nombreUsuario.isEmpty()) {
                nombre.setError("El nombre es obligatorio");
                return;
            }

            // Validación de correo
            //el matcher sirve para validar el formato del correo
            if (correoUsuario.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correoUsuario).matches()) {
                correo.setError("Ingresa un correo válido");
                return;
            }

            // Validación de contraseña
            if (contrasenaUsuario.isEmpty() || contrasenaUsuario.length() < 4) {
                contrasena.setError("La contraseña debe tener al menos 4 caracteres");
                return;
            }

            // Validación de teléfono
            if (telefonoUsuario.isEmpty() || !telefonoUsuario.matches("\\d{9,}")) {
                telefono.setError("El número de teléfono debe tener al menos 9 dígitos y solo contener números");
                return;
            }

            if (esFundacion) {
                String direccionF = direccion.getText().toString();
                String categoriaSeleccionada = categoria.getSelectedItem().toString();

                // Validación de dirección
                if (direccionF.isEmpty()) {
                    direccion.setError("La dirección es obligatoria para fundaciones");
                    return;
                }

                // Validación de categoría
                if (categoriaSeleccionada.isEmpty()) {
                    Toast.makeText(RegistroCuenta.this, "Por favor, selecciona una categoría", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Guardar en SharedPreferences (si todas las validaciones son correctas)
            SharedPreferences preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("nombre", nombreUsuario);
            editor.putString("correo", correoUsuario);
            editor.putString("contrasena", contrasenaUsuario);
            editor.putString("telefono", telefonoUsuario);
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

    }};
