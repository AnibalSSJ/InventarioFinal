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
import android.widget.ImageView;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarProductoActivity extends AppCompatActivity {
    Button btn_add;
    Button btnIMG;

    EditText nombreProducto, precioProductoUnidad, cantidadProducto,precioProductoConjunto;
    Spinner spinnertipo;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        String id = getIntent().getStringExtra("id_producto");
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mStorage= FirebaseStorage.getInstance().getReference();

        btnIMG= findViewById(R.id.btn_photo);
        mProgressDialog = new ProgressDialog(this);

        nombreProducto=(EditText) findViewById(R.id.eNombreProducto);
        precioProductoUnidad=(EditText) findViewById(R.id.ePrecioUnidad);
        cantidadProducto=(EditText) findViewById(R.id.eCantidad);
        precioProductoConjunto=(EditText) findViewById(R.id.ePrecioConjunto);
        spinnertipo = (Spinner) findViewById(R.id.spinner);
        String[] opciones ={"Prioritario", "Secundario","Desechables", "Limpieza" };
        ArrayAdapter<String> arreglo1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinnertipo.setAdapter(arreglo1);
        btn_add= findViewById(R.id.bAgregarProducto);

        btnIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });



            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = nombreProducto.getText().toString().trim();
                    String cantidad = cantidadProducto.getText().toString().trim();
                    String precioUnidad = precioProductoUnidad.getText().toString().trim();
                    String precioConjunto = precioProductoConjunto.getText().toString().trim();
                    String seleccion = spinnertipo.getSelectedItem().toString().trim();


                    if (nombre.isEmpty() && cantidad.isEmpty() && precioUnidad.isEmpty() && precioConjunto.isEmpty() && seleccion.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        postProducto(nombre, cantidad, precioUnidad, precioConjunto, seleccion);
                    }
                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setTitle("Agregando Imagen...");
            mProgressDialog.setMessage("Agregando imagen al inventario");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();


            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressDialog.dismiss();
                    Toast.makeText(AgregarProductoActivity.this, "Imagen Agregada Con Exito", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void postProducto(String nombre, String cantidad, String precioUnidad, String precioConjunto, String seleccion) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombreProducto", nombre);
        map.put("cantidadProducto", cantidad);
        map.put("precioProductoUnidad", precioUnidad);
        map.put("precioProductoConjunto", precioConjunto);
        map.put("spinnerTipo", seleccion);



        mFirestore.collection("Producto").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar producto", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void volverAtras(View view){
        Intent volverAtaras = new Intent(this, ActivityDos.class);
        startActivity(volverAtaras);
    }
}