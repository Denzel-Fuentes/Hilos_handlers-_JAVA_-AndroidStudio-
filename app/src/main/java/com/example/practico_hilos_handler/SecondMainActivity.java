package com.example.practico_hilos_handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SecondMainActivity extends AppCompatActivity {
    String[] descriptions={
            "Crea una aplicación que descargue imágenes en segundo plano utilizando tanto Threads como Coroutines. Muestra las imágenes en una lista o cuadrícula cuando se descarguen.",
            "Crea una aplicación de chat simple en la que los mensajes enviados desde un dispositivo se muestren en otro dispositivo utilizando tanto Handlers como Coroutines para actualizar la interfaz de usuario.",
            "Crea una aplicación que envíe mensajes entre dos actividades utilizando Bundles en los intents para pasar datos, y además, utiliza Coroutines para realizar tareas asincrónicas.",
            "Crea una aplicación que muestre notificaciones periódicas utilizando tanto Handlers como Coroutines, y un servicio en segundo plano para mantener actualizado el contador de tiempo",
            "Crea una aplicación de cronómetro que permita a los usuarios iniciar, pausar y reiniciar el cronómetro utilizando tanto Handlers como Coroutines para realizar el seguimiento del tiempo transcurrido.",
            "Crea una aplicación que permita a los usuarios descargar archivos grandes y muestre el progreso de la descarga utilizando tanto Handlers como Executors para realizar la tarea de descarga.",
            "Crea una aplicación que administre una lista de tareas pendientes y permita al usuario agregar y eliminar tareas utilizando tanto Executors como Coroutines para manejar las tareas en segundo plano.",
            "Crea una aplicación que permita a los usuarios cargar varias imágenes y realice un procesamiento en paralelo utilizando tanto Executors como Coroutines para acelerar el procesamiento.",
            "Encuentra el numero mayor de en un arreglo de números enteros.",
            "Suma los primeros 'n' números naturales.",
            "Calcula el máximo común divisor (MCD) de dos números.",
            "Imprime una cadena al revés usando recursión.",

    };
    String[] titles= {
            "Descarga de Imágenes utilizando Threads y Coroutines",
            "Chat Simple con Handlers y Coroutines",
            "Envío de Mensajes entre Actividades con Bundles y Coroutines",
            "Notificaciones Periódicas con Handlers y Coroutines",
            "Cronómetro con Handlers y Coroutines",
            "Descarga de Archivos con Handlers y Executors",
            "Administrador de Tareas con Executors y Coroutines",
            "Procesamiento de Imágenes en Paralelo con Executors y Coroutines",
            "Recursividad"
    };
    Button btnBackToMenu;
    TextView txtTittle;
    LinearLayout section;
    Component component;
    Handler handler;
    int option=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
        handler=new Handler(Looper.getMainLooper());
        txtTittle = findViewById(R.id.txtTittle);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        section = findViewById(R.id.layoutComponent);
        String message=null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            message = bundle.getString("message");
            option= bundle.getInt("option");
        }
        txtTittle.setText("Ejercicio "+option);
        txtTittle.setTypeface(null, Typeface.BOLD);
        initProyect(option);
        if(message!=null && option==3){
            final String mensaje=message;
            handler.post(new Runnable() {
                int time=0;
                @Override
                public void run() {
                    if(time==3){
                        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                    } else if (time<=3) {
                        time++;
                        handler.postDelayed(this,1000);
                    }

                }
            });
        }
        btnBackToMenu.setOnClickListener(view -> finish());
    }
    public void initProyect(int option){
        component = new Component(section);
        switch (option) {
            case 1:
                title(option);
                description(option);
                component.addButton("btn1","Descargar",v->ej1());
                break;
            case 2:
                title(option);
                description(option);
                component.addButton("btn2","Iniciar Chat",view -> ej2());
                break;
            case 3:
                title(option);
                description(option);
                component.addEditText("txt1");
                component.addButton("btn3","Enviar Mensaje",view -> ej3());
                break;
            case 4:
                title(option);
                description(option);
                activityTime();
                component.addButton("bnt4","Iniciar Notificaciones",view -> ej4());
                break;
            case 5:
                title(option);
                description(option);
                break;
            case 6:
                title(option);
                description(option);
                break;
            case 7:
                title(option);
                description(option);
                break;
            case 8:
                title(option);
                description(option);
                break;
            case 9:
                title(option);
                description(option);
                break;
            case 10:
                title(9);
                description(option);
                break;
            case 11:
                title(9);
                description(option);
                break;
            case 12:
                title(9);
                description(option);
                component.addEditText("txt12");
                component.addButton("btn12","Imprimir",view -> ej12());
                break;
            default:
                // Manejar opción no válida si es necesario
                break;
        }
        component.render();
    }
    public void title(int t){
        TextView title=new TextView(this);
        title.setText(titles[t-1]);
        title.setTextSize(20);
        title.setTypeface(null, Typeface.BOLD);
        section.addView(title);
    }
    public void description(int d){
        TextView description=new TextView(this);
        description.setText(descriptions[d-1]);
        description.setTextSize(15);
        section.addView(description);
    }
    public void activityTime(){
        TextView time=new TextView(this);
        time.post(new Runnable() {
            int seconds=0;
            @Override
            public void run() {
                time.setText("Tiempo transcurrido: "+seconds);
                time.postDelayed(this,1000);
                seconds++;
            }
        });
        section.addView(time);
    }
    public void hola(){
        /**
         * Usamos la funcion getViewById para obtener el componente que creamos pasando
         * como parametro el ID que le asignamos y lo casteamos
         * al tipo de objeto al que pertenece Button, TextView,etc ...**/
        TextView txt  = (TextView) component.getViewById("txt1");
        txt.setText("Brutal todo");
        Toast.makeText(this,"holaaaa",Toast.LENGTH_SHORT).show();
    }
    public void ej1(){
        /*
        Esto seria la forma de hacerlo con Handlers y Runnables
        Runnable runnable = new Runnable() {
        TextView t = new TextView(getApplicationContext());
        int porcentaje = 0;

            @Override
            public void run() {
                if (porcentaje <= 100) {
                section.removeView(t);
                t.setText("Descargando Imagen: " + porcentaje + "%");
                section.addView(t);
                porcentaje += 25;
                handler.postDelayed(this, 1000);
                } else {
                    section.removeView(t);
                }
            }
        };
        handler.post(runnable);*/
        new Thread(new Runnable() {
            TextView t=new TextView(getApplicationContext());
            int porcentaje=0;
            @Override
            public void run() {
                while (porcentaje<=100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            section.removeView(t);
                            t.setText("Descargando Imagen: " + porcentaje + "%");
                            section.addView(t);
                            porcentaje+=25;
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        section.removeView(t);
                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setImageResource(R.drawable.ic_launcher_background); // R.drawable.my_image es el ID de la imagen en los recursos
                        imageView.setPadding(0,20,0,20);
                        section.addView(imageView);
                    }
                });

            }
        }).start();
    }
    public void ej2(){
        String[] conversation = {
                "Patito Juan \n¡Hola! ¿Cómo estás?",
                "Patito Miguel \n ¡Hola! Estoy bien, ¿y tú?",
                "Patito Juan \nTambién estoy bien, gracias.",
                "Patito Miguel \n ¿Qué has estado haciendo hoy?",
                "Patito Juan \nHe estado trabajando en un proyecto de programación.",
                "Patito Miguel \n ¡Eso suena interesante! Yo fui al gimnasio esta mañana.",
                "Patito Juan \n¡Genial! Mantenerse activo es importante.",
                "Patito Miguel \n Sí, definitivamente. ¡Hablemos luego!",
                "Patito Juan \nClaro, ¡hablamos luego! ¡Adiós!"
        };

        List<TextView> textViews=new ArrayList<>();
        for(int i=0;i<9;i++){
            TextView t=new TextView(this);
            t.setId(i);
            t.setText(conversation[i]);
            t.setPadding(0,20,0,20);
            if(i%2!=0){
                t.setGravity(Gravity.END);
            }
            textViews.add(t);
        }
        Runnable runnable=new Runnable() {
            int quantity=0;
            LinearLayout layout = new LinearLayout(getApplicationContext());
            @Override
            public void run() {
                layout.setOrientation(LinearLayout.VERTICAL);
                section.removeView(layout);
                if(quantity<textViews.size()){
                    layout.addView(textViews.get(quantity));
                    quantity++;
                }else{
                    layout.removeAllViews();
                    quantity=0;
                }
                section.addView(layout);
                handler.postDelayed(this,1500);
            }
        };
        handler.post(runnable);
    }
    public void ej3(){
        EditText editText  = (EditText) component.getViewById("txt1");
        Intent intent = new Intent(SecondMainActivity.this, MainActivity.class);
        intent.putExtra("message",editText.getText().toString());
        startActivity(intent);
    }
    public void ej4(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isFinishing()==false){
                    handler.postDelayed(this,3000);
                    Notification.showHeadUpNotification(getApplicationContext(), "Título de la Notificación", "Contenido de la notificación");
                }
            }
        });
    }
    public void ej12(){
        Recursividad recursividad=new Recursividad();
        EditText editText= (EditText) component.getViewById("txt12");
        recursividad.setText(editText.getText().toString());
        ExecutorService service= Executors.newSingleThreadExecutor();
        Future<Recursividad.Functions> result=service.submit(recursividad);
        handle(result);
    }
    public void handle(Future<Recursividad.Functions> future){
        handler.post(new Runnable() {
            TextView textView=new TextView(getApplicationContext());
            String text="";
            @Override
            public void run() {
                try {
                    Recursividad.Functions responsive=future.get();
                    switch (option){
                        case 12:
                            text=responsive.getInvertText();
                            break;
                    }
                    String result=text.isEmpty()?"No se ingreso nada":"Resultado: "+text;
                    textView.setText(result);
                    section.addView(textView);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}