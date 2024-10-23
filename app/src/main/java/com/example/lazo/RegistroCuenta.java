package com.example.lazo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lazo.modelo.Fundacion;
import com.example.lazo.modelo.Usuario;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroCuenta extends AppCompatActivity {

    private LinearLayout fundacionCamposExtra;
    private EditText nombre, correo, contrasena, direccion, telefono, descripcion;
    private Spinner categoria;
    private Switch switchTipoUsuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        nombre = findViewById(R.id.registro_nombre);
        correo = findViewById(R.id.registro_correo);
        contrasena = findViewById(R.id.registro_contrasenha);
        direccion = findViewById(R.id.registro_direccion);
        categoria = findViewById(R.id.spinner_categoria);
        telefono = findViewById(R.id.registro_telefono);
        descripcion = findViewById(R.id.registro_descripcion);
        Button botonRegistro = findViewById(R.id.boton_registro);
        switchTipoUsuario = findViewById(R.id.switch_tipo_usuario);
        fundacionCamposExtra = findViewById(R.id.fundacion_campos_extra);

        switchTipoUsuario.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fundacionCamposExtra.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        botonRegistro.setOnClickListener(v -> {
            String nombreUsuario = nombre.getText().toString();
            String correoUsuario = correo.getText().toString();
            String contrasenaUsuario = contrasena.getText().toString();
            String telefonoUsuario = telefono.getText().toString();
            boolean esFundacion = switchTipoUsuario.isChecked();

            if (nombreUsuario.isEmpty()) {
                nombre.setError("El nombre es obligatorio");
                return;
            }
            if (correoUsuario.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correoUsuario).matches()) {
                correo.setError("Ingresa un correo válido");
                return;
            }
            if (contrasenaUsuario.isEmpty() || contrasenaUsuario.length() < 4) {
                contrasena.setError("La contraseña debe tener al menos 4 caracteres");
                return;
            }
            if (telefonoUsuario.isEmpty() || !telefonoUsuario.matches("\\d{9,}")) {
                telefono.setError("El número de teléfono debe tener al menos 9 dígitos y solo contener números");
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference reference;
            String fotoPerfilPredeterminada = "https://firebasestorage.googleapis.com/v0/b/lazo-db1c4.appspot.com/o/Fotos%20de%20%20perfil%2Fsinfoto.png?alt=media&token=6cb09d15-45ce-44e1-8d91-e48d6d8568d7";

            if (esFundacion) {
                String direccionF = direccion.getText().toString();
                String categoriaSeleccionada = categoria.getSelectedItem().toString();
                String descripcionF = descripcion.getText().toString();

                if (direccionF.isEmpty()) {
                    direccion.setError("La dirección es obligatoria");
                    return;
                }

                Fundacion fundacion = new Fundacion(
                        nombreUsuario,
                        correoUsuario,
                        contrasenaUsuario,
                        telefonoUsuario,
                        fotoPerfilPredeterminada,
                        direccionF,
                        categoriaSeleccionada,
                        descripcionF
                );

                reference = db.collection("fundaciones");
                reference.add(fundacion)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(RegistroCuenta.this, "Registro de fundación exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistroCuenta.this, MainActivity.class));
                            finish();
                        });

            } else {
                Usuario usuario = new Usuario(
                        nombreUsuario,
                        correoUsuario,
                        contrasenaUsuario,
                        telefonoUsuario,
                        fotoPerfilPredeterminada
                );

                reference = db.collection("usuarios");
                reference.add(usuario)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(RegistroCuenta.this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistroCuenta.this, MainActivity.class));
                            finish();
                        });
            }
        });
    }
}
