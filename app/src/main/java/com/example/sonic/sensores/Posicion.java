package com.example.sonic.sensores;

/**
 * Created by sonic on 18/05/2017.
 */

public class Posicion {
    static final RangoPosicion PARADO = new RangoPosicion(-2.0f,2.0f,8.0f,10.0f,-2.0f,2.0f);
    static final RangoPosicion ACOSTADO = new RangoPosicion(-2.0f,2.0f,-2.0f,2.0f,8.0f,11.0f);
    static final RangoPosicion IZQUIERDA = new RangoPosicion(8.0f,11.0f,-3.0f,3.0f,-4.0f,4.0f);
    static final RangoPosicion DERECHA = new RangoPosicion(-10.0f,-8.0f,-2.0f,2.0f,-3.0f,3.0f);
    static final RangoPosicion DECABEZA = new RangoPosicion(-2.0f,2.0f,-10.0f,-8.0f,-3.0f,3.0f);

    RangoPosicion rango;

    public Posicion(RangoPosicion rango) {
        this.rango = rango;
    }

    public boolean coordenadaValida(float x, float y, float z){
        if(x>rango.getMenorX() && x<rango.getMayorX()){
            if(y>rango.getMenorY() && y<rango.getMayorY()){
                if(z>rango.getMenorZ() && z<rango.getMayorZ()){
                    return true;
                }
            }
        }
        return false;
    }
}
