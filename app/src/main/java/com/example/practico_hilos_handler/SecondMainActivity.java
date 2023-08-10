package com.example.practico_hilos_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondMainActivity extends AppCompatActivity {
    Button btnBackToMenu;
    TextView txtTittle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
        txtTittle = findViewById(R.id.txtTittle);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        // 0 es el valor por defecto si no se encuentra "option"
        Intent intent = getIntent();
        int option = intent.getIntExtra("option", 0);
        txtTittle.setText("Ejercicio "+option);

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}