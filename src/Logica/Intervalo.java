/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Intervalo {
    double min;
    double max;
    ArrayList<Double> datosIntervalo;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
    public ArrayList<Double> getDatosIntervalo(){
        return datosIntervalo;
    }

    public void setDatosIntervalo(ArrayList<Double> datosIntervalo) {
        this.datosIntervalo = datosIntervalo;
    }
    public void anadirDatos(Double i) {
        this.datosIntervalo.add(i);
    }
}
