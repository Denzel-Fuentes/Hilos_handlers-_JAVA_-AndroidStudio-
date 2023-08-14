package com.example.practico_hilos_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


import android.os.Message;
import android.view.Gravity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
        handler=new Handler(Looper.getMainLooper());
        txtTittle = findViewById(R.id.txtTittle);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        section = findViewById(R.id.layoutComponent);
        // 0 es el valor por defecto si no se encuentra "option"
        Intent intent = getIntent();
        int option = intent.getIntExtra("option", 0);
        txtTittle.setText("Ejercicio "+option);
        txtTittle.setTypeface(null, Typeface.BOLD);
        btnBackToMenu.setOnClickListener(view -> finish());
        initProyect(option);
    }
    public void initProyect(int option){
        /*Instanciamos la clase y le pasamos como parametro el
          Linearlayout donde agregaremos etiquetas con sus propiedades*/
        component = new Component(section);
        switch (option) {
            case 1:
                title(option);
                description(option);
                component.addEditText("input1");
                component.addEditText("input2");
                //component.addEditText("input1");
                //component.addEditText("input1");
                /**pasamos como parametro el ID , texto del boton y su eventoClick
                 * que debe de ser una expresion lambda con una funcion
                 * Dentro de la funcion se muestra como acceder a los componentes
                 * creados con la clase*/
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

                break;
            case 4:
                title(option);
                description(option);
                break;
            case 5:
                title(option);
                description(option);
                Ej5_cronometro cronometro = new Ej5_cronometro();
                component.addTextView("txtTiempo","");
                component.addButton("btnDetener","Detener",v->cronometro.detener());
                component.addButton("btnIniciar","Iniciar",v->cronometro.Comenzar());
                component.addButton("btnReiniciar","Reiniciar",v->cronometro.reiniciar());
                break;
            case 6:
                title(option);
                description(option);
                component.addEditText("input1");
                component.addButton("","Descargar",v->ej6());
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
        EditText input = (EditText) component.getViewById("input1");
        txt.setText(input.getText().toString());
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
    class Ej5_cronometro{
        private int minutos , segundos,horas;
        private boolean isRunning;
        private Handler handler;
        private Runnable runnable;
        Ej5_cronometro(){this.minutos = 0;this.horas = 0;this.segundos =0;this.handler = new Handler();this.isRunning =false;}
        private TextView txtTiempo;
        public void detener(){
            this.txtTiempo = (TextView) component.getViewById("txtTiempo");
            if (isRunning) {
                handler.removeCallbacks(runnable);
                //elapsedTime = System.currentTimeMillis() - startTime;
                isRunning = false;
            }
        }
        public void Comenzar(){
            this.txtTiempo = (TextView) component.getViewById("txtTiempo");
            if (!isRunning){
                isRunning = true;
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (segundos == 59) {
                            segundos = 0;
                            if (minutos == 59) {
                                minutos = 0;
                                horas++;
                            } else {
                                minutos++;
                            }
                        } else {
                            segundos++;
                        }
                        actualizarTiempo(horas,minutos,segundos);
                        handler.postDelayed(this, 1000);
                    }
                };
                handler.postDelayed(runnable, 0);
            }
        }
        public void reiniciar(){
            this.txtTiempo = (TextView) component.getViewById("txtTiempo");
            if (!isRunning) {
                actualizarTiempo(0,0,0);
            }
        }
        private void actualizarTiempo(int horas , int minutos, int segundos) {
            final String timeText = String.format("%02d:%02d:%02d",horas,  minutos, segundos);
            txtTiempo.post(new Runnable() {
                @Override
                public void run() {
                    txtTiempo.setText(timeText);
                }
            });
        }
    }
    public void ej6(){
        /**
         * usuarios descargar archivos grandes y muestre el progreso de la
         * descarga utilizando tanto Handlers como Executors para realizar
         * la tarea de descarga.*/
        ExecutorService executor;
        EditText input1 = (EditText) component.getViewById("input1");
        TextView txt = component.addTextView(null,"1");
        final int size =Integer.parseInt(input1.getText().toString());
        executor = Executors.newFixedThreadPool(1);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    txt.setText("Porcentaje: "+msg.arg1+"%  Progreso: "+msg.arg2 + " Estado: Descargado ✅ ");
                }else {
                    txt.setText("Porcentaje: "+msg.arg1+"%  Progreso: "+msg.arg2 + " Estado: Descargando.... ");
                }
                return true;
            }

        });
        executor.execute((new Runnable() {
            int progress;
            @Override
            public void run() {
                while (progress < size){
                    progress++;
                    int percentage = (progress*100)/size;
                    Message message = new Message();
                    message.arg1 = percentage;
                    message.arg2 = progress;
                    if(progress == size){
                        message.what = 1;
                    }else {message.what =0;}

                    handler.sendMessage(message);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }));
    }

}