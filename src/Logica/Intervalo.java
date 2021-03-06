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
    
    double frecuenciaAbsoluta;
    double frecuenciaAbsolutaAcumulada;
    double frecuenciaRelativa;
    double frecuenciaRelativaAcumulada;
    double frecuenciaEsperada;
    double frecuenciaEsperadaAcumulada;
    double chi2;
    
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

    public double getFrecuenciaAbsoluta() {
        return frecuenciaAbsoluta;
    }

    public void setFrecuenciaAbsoluta(double frecuenciaAbsoluta) {
        this.frecuenciaAbsoluta = frecuenciaAbsoluta;
    }

    public double getFrecuenciaAbsolutaAcumulada() {
        return frecuenciaAbsolutaAcumulada;
    }

    public void setFrecuenciaAbsolutaAcumulada(double frecuenciaAbsolutaAcumulada) {
        this.frecuenciaAbsolutaAcumulada = frecuenciaAbsolutaAcumulada;
    }

    public double getFrecuenciaRelativa() {
        return frecuenciaRelativa;
    }

    public void setFrecuenciaRelativa(double frecuenciaRelativa) {
        this.frecuenciaRelativa = frecuenciaRelativa;
    }

    public double getFrecuenciaRelativaAcumulada() {
        return frecuenciaRelativaAcumulada;
    }

    public void setFrecuenciaRelativaAcumulada(double frecuenciaRelativaAcumulada) {
        this.frecuenciaRelativaAcumulada = frecuenciaRelativaAcumulada;
    }
     
    public void anadirDatos(Double i) {
        getDatosIntervalo().add(i);
    }
    public void calcularFrecuenciaAbsoluta(){
        setFrecuenciaAbsoluta(datosIntervalo.size());
    }

    public double getFrecuenciaEsperada() {
        return frecuenciaEsperada;
    }

    public void setFrecuenciaEsperada(double frecuenciaEsperada) {
        this.frecuenciaEsperada = frecuenciaEsperada;
    }

    public double getFrecuenciaEsperadaAcumulada() {
        return frecuenciaEsperadaAcumulada;
    }

    public void setFrecuenciaEsperadaAcumulada(double frecuenciaEsperadaAcumulada) {
        this.frecuenciaEsperadaAcumulada = frecuenciaEsperadaAcumulada;
    }

    public double getChi2() {
        return chi2;
    }

    public void setChi2(double chi2) {
        this.chi2 = chi2;
    }
    
}
