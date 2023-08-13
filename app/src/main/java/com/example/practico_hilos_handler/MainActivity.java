package com.example.practico_hilos_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEjercicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 1; i <= 12; i++) {
            int buttonId = getResources().getIdentifier("btnEjercicio_" + i, "id", getPackageName());
            btnEjercicio = findViewById(buttonId);
            btnEjercicio.setOnClickListener(this);
        }
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String mensaje = bundle.getString("message");
            System.out.println(mensaje);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                            }
                        });
                        Thread.sleep(2500);
                        Intent intent =new Intent(MainActivity.this,SecondMainActivity.class);
                        intent.putExtra("message","Main: Tengo Hambre");
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        String buttonIdString = getResources().getResourceEntryName(buttonId);

        int option = Integer.parseInt(buttonIdString.substring("btnEjercicio_".length()));

        Intent intent = new Intent(MainActivity.this, SecondMainActivity.class);
        intent.putExtra("option", option); // Envía el número de opción
        startActivity(intent);
    }
}