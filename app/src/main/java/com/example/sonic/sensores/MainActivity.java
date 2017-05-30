package com.example.sonic.sensores;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    float x = 0;
    float y = 0;
    float z = 0;
    TextView datos,movimiento,puntos, tiempo;
    boolean jugando = false;
    private SensorManager mSensor;
    Button empezar;
    int posicion = 0;
    int pasado = 3;
    CountDownTimer cronometro;

    Posicion[] posiciones;
    String sposiciones[];
    long puntuacion;
    MediaPlayer sonidos[];
    /*
        ejes x,y,z
        posicion 0 parado
        posicion 1 acostado
        posicion 2 izquierda
        posicion 3 derecha
        posicion 4 de cabeza
    */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = (TextView) findViewById(R.id.datos);
        empezar = (Button)findViewById(R.id.empezar);
        movimiento =(TextView)findViewById(R.id.movimiento);
        puntos = (TextView)findViewById(R.id.puntuacion);
        tiempo=(TextView)findViewById(R.id.tiempo);
        mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor.registerListener(this,
                mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        posiciones = new Posicion[4];

        posiciones[0]= new Posicion(Posicion.ACOSTADO);
        posiciones[1]= new Posicion(Posicion.IZQUIERDA);
        posiciones[2] =new Posicion(Posicion.DERECHA);
        posiciones[3] = new Posicion(Posicion.PARADO);
        sposiciones=new String[4];

        sposiciones[0]="ABAJO";
        sposiciones[1]="IZQUIERDA";
        sposiciones[2]="DERECHA";
        sposiciones[3]="REGRESA";

        sonidos = new MediaPlayer[4];
        sonidos[0] = MediaPlayer.create(this, R.raw.abajo);
        sonidos[1] = MediaPlayer.create(this, R.raw.izquierda);
        sonidos[2] = MediaPlayer.create(this, R.raw.derecha);
        sonidos[3] = MediaPlayer.create(this, R.raw.regresa);
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jugando){
                    posicion= (int)(Math.random()*3);
                    pasado = 3;
                    empezar.setText("FINALIZAR");
                    jugando = true;
                    movimiento.setTextColor(Color.BLACK);
                    movimiento.setText(sposiciones[posicion]);
                    sonidos[posicion].start();
                    puntuacion=0;
                    puntos.setText(puntuacion+"");
                    cronometro();

                }else{
                    empezar.setText("EMPIEZA EL JUEGO");
                    jugando = false;

                    movimiento.setTextColor(Color.BLACK);
                    puntos.setText(puntuacion+"");
                    cronometro.cancel();

                }
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        this.x = event.values[SensorManager.DATA_X];
        this.y = event.values[SensorManager.DATA_Y];
        this.z = event.values[SensorManager.DATA_Z];
        datos.setText("X = " + x +
                "\nY = " + y +
                "\nZ = " + z);
        if(jugando) {

                  for(int i=0;i<posiciones.length;i++){

                      if(posiciones[i].coordenadaValida(x,y,z)){

                          if(i==posicion&&i!=pasado){
                                if(posicion==3){
                                puntuacion++;
                                puntos.setText(puntuacion+"");
                                  pasado = posicion;
                                  posicion= (int)(Math.random()*3);
                                    movimiento.setText(sposiciones[posicion]);
                                    cronometro.cancel();
                                    sonidos[posicion].start();
                                  cronometro();

                                }else{
                                  pasado = posicion;
                                  posicion=3;
                                  movimiento.setText(sposiciones[3]);
                                    sonidos[posicion].start();
                                }
                                break;
                          }else{if(i!=pasado){
                              jugando=false;
                              movimiento.setTextColor(Color.RED);
                              movimiento.setText("HAS PERDIDO");
                              empezar.setText("EMPIEZA EL JUEGO");
                              cronometro.cancel();
                              break;

                          }}
                      }


        }

    }}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void cronometro(){
        cronometro = new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                tiempo.setText(""+TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                jugando=false;
                movimiento.setTextColor(Color.RED);
                movimiento.setText("HAS PERDIDO");
                empezar.setText("EMPIEZA EL JUEGO");
            }
        }.start();

    }
}
