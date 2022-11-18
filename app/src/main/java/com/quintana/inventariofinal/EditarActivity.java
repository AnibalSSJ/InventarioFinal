package com.quintana.inventariofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.quintana.inventariofinal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {
    Button bActualizar;
    EditText EnombreProducto, EprecioProductoUnidad, EcantidadProducto,EprecioProductoConjunto;
    Spinner Espinnertipo;
    private String productoId;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        EnombreProducto=(EditText) findViewById(R.id.eNombreProducto);
        EprecioProductoUnidad=(EditText) findViewById(R.id.ePrecioUnidad);
        EcantidadProducto=(EditText) findViewById(R.id.eCantidad);
        EprecioProductoConjunto=(EditText) findViewById(R.id.ePrecioConjunto);
        Espinnertipo = (Spinner) findViewById(R.id.spinner);
        String[] opciones ={"Prioritario", "Secundario","Desechables", "Limpieza" };
        ArrayAdapter<String> arreglo1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        Espinnertipo.setAdapter(arreglo1);
        bActualizar = findViewById(R.id.bActualizar);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        productoId = getIntent().getStringExtra("productoId");

        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = EnombreProducto.getText().toString().trim();
                String cantidad = EcantidadProducto.getText().toString().trim();
                String precioUnidad = EprecioProductoUnidad.getText().toString().trim();
                String precioConjunto = EprecioProductoConjunto.getText().toString().trim();
                String seleccion = Espinnertipo.getSelectedItem().toString().trim();


                if (nombre.isEmpty() && cantidad.isEmpty() && precioUnidad.isEmpty() && precioConjunto.isEmpty() && seleccion.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postProducto(nombre, cantidad, precioUnidad, precioConjunto, seleccion);
                }

            }
        });


    }

    private void postProducto(String nombre, String cantidad, String precioUnidad, String precioConjunto, String seleccion){
        Map<String, Object> map = new HashMap<>();
        map.put("nombreProducto", nombre);
        map.put("cantidadProducto", cantidad);
        map.put("precioProductoUnidad", precioUnidad);
        map.put("precioProductoConjunto", precioConjunto);
        map.put("spinnerTipo", seleccion);

        mFirestore.collection("Producto").document(productoId).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditarActivity.this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(EditarActivity.this, MisProductos.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarActivity.this, "Error al actualizar producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void volverAtras(View view){
        Intent volverAtaras = new Intent(this, MisProductos.class);
        startActivity(volverAtaras);
    }
}