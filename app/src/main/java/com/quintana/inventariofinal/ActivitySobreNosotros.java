package com.quintana.inventariofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.quintana.inventariofinal.R;

public class ActivitySobreNosotros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nosotros);
    }

    public void volverMenu(View view) {
        Intent volverMenu = new Intent(this, ActivityDos.class);
        startActivity(volverMenu);
    }
}