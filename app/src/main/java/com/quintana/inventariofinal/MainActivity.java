package com.quintana.inventariofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quintana.inventariofinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
Button btn_login;
Button cboton;
EditText email, password;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.edcorreo);
        password = findViewById(R.id.edpassword);
        btn_login = findViewById(R.id.btn_ingresar);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String emailUser = email.getText().toString().trim();
               String passUser = password.getText().toString().trim();

               if (emailUser.isEmpty() && passUser.isEmpty()){
                   Toast.makeText(MainActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
               }else{
                    loginUser(emailUser, passUser);
               }
            }
        });

        cboton=(Button) findViewById(R.id.bcrear);
        cboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, ActivityDos.class));
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this, ActivityDos.class));
            finish();
        }
    }
}