package com.example.sonic.sensores;

/**
 * Created by sonic on 18/05/2017.
 */

public class RangoPosicion {
    private float menorX, mayorX;
    private float menorY, mayorY;
    private float menorZ, mayorZ;


    public RangoPosicion(float menorX, float mayorX, float menorY, float mayorY, float menorZ, float mayorZ) {
        this.menorX = menorX;
        this.mayorX = mayorX;
        this.menorY = menorY;
        this.mayorY = mayorY;
        this.menorZ = menorZ;
        this.mayorZ = mayorZ;
    }

    public float getMenorX() {
        return menorX;
    }

    public void setMenorX(float menorX) {
        this.menorX = menorX;
    }

    public float getMayorX() {
        return mayorX;
    }

    public void setMayorX(float mayorX) {
        this.mayorX = mayorX;
    }

    public float getMenorY() {
        return menorY;
    }

    public void setMenorY(float menorY) {
        this.menorY = menorY;
    }

    public float getMayorY() {
        return mayorY;
    }

    public void setMayorY(float mayorY) {
        this.mayorY = mayorY;
    }

    public float getMenorZ() {
        return menorZ;
    }

    public void setMenorZ(float menorZ) {
        this.menorZ = menorZ;
    }

    public float getMayorZ() {
        return mayorZ;
    }

    public void setMayorZ(float mayorZ) {
        this.mayorZ = mayorZ;
    }
}
