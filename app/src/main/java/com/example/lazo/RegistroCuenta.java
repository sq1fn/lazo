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

import com.example.lazo.modelo.Fundacion;
import com.example.lazo.modelo.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

            // Referencia a Firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference;

            if (esFundacion) {
                String direccionF = direccion.getText().toString();
                String categoriaSeleccionada = categoria.getSelectedItem().toString();

                // Validaciones adicionales para la fundación
                if (direccionF.isEmpty()) {
                    direccion.setError("La dirección es obligatoria");
                    return;
                }

                Fundacion fundacion = new Fundacion(nombreUsuario, correoUsuario, contrasenaUsuario, telefonoUsuario, direccionF, categoriaSeleccionada);
                reference = database.getReference("users/fundaciones");
                reference.child(nombreUsuario).setValue(fundacion);

            } else {
                Usuario usuario = new Usuario(nombreUsuario, correoUsuario, contrasenaUsuario, telefonoUsuario);
                reference = database.getReference("users/usuarioStandar");
                reference.child(nombreUsuario).setValue(usuario);
            }

            Toast.makeText(RegistroCuenta.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroCuenta.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }};
