package com.quintana.inventariofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityDos extends AppCompatActivity implements BiometricCallback {
    Button btnCerrar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);
        mAuth= FirebaseAuth.getInstance();


        btnCerrar= findViewById(R.id.btnCerrar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(ActivityDos.this, MainActivity.class));
            }
        });
        new BiometricManager.BiometricBuilder(ActivityDos.this)
                .setTitle("Logueo")
                .setSubtitle("APP INVENTARIO")
                .setDescription("Ingresa la huella digital")
                .setNegativeButtonText("Cancelar")
                .build()
                .authenticate(ActivityDos.this);
    }

    public void agregarProducto(View view) {
        Intent agregarProducto = new Intent(this, AgregarProductoActivity.class);
        startActivity(agregarProducto);
    }

    public void sobreNosotros(View view) {
        Intent sobreNosotros = new Intent(this, ActivitySobreNosotros.class);
        startActivity(sobreNosotros);
    }

    public void misProductos(View view) {
        Intent misProductos = new Intent(this, MisProductos.class);
        startActivity(misProductos);
    }
    public void dondeNosUbicamos(View view){
        Intent dondeNosUbicamos= new Intent(this, MapsActivity.class);
        startActivity(dondeNosUbicamos);
    }

    @Override
    public void onSdkVersionNotSupported() {

    }

    @Override
    public void onBiometricAuthenticationNotSupported() {

    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {

    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {

    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {

    }

    @Override
    public void onAuthenticationFailed() {

    }

    @Override
    public void onAuthenticationCancelled() {

    }

    @Override
    public void onAuthenticationSuccessful() {
        Toast.makeText(this, "Ingresó Correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

    }
}