/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;
import java.lang.Integer;
import static java.lang.Math.log;
import java.util.Collections;
/**
 *
 * @author diego
 */
public class Datos {
    
    int i = 1;
    int j=0;
    int w=0;
    ArrayList<Double> datos;
    int numeroIntervalos = 0;
    double tamañoIntervalo = 0;
    ArrayList<Intervalo> datosAgrupados;

    public Datos() {
        datos = new ArrayList(); 
        datosAgrupados = new ArrayList();
    }

    public ArrayList<Double> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Double> datos) {
        this.datos = datos;
    }

    public int getNumeroIntervalos() {
        return numeroIntervalos;
    }

    public void setNumeroIntervalos(int numeroIntervalos) {
        this.numeroIntervalos = numeroIntervalos;
    }

    public double getTamañoIntervalo() {
        return tamañoIntervalo;
    }

    public void setTamañoIntervalo(double tamañoIntervalo) {
        this.tamañoIntervalo = tamañoIntervalo;
    }
    
    public void anadirDatos(Double i) {
        this.datos.add(i);
    }
    
    public void calcularnumeroDeIntervalo(){
        this.numeroIntervalos= (int) (1+3.3*log(this.datos.size()+0.5));
    }
    
    public void calcularTamañoIntervalo(){
        setTamañoIntervalo((Collections.max(datos)-Collections.min(datos))/getNumeroIntervalos());
    }

    public void crearIntevalos(){
        this.datosAgrupados.get(0).setMin(Collections.min(datos));
        datosAgrupados.get(0).setMax(Collections.min(datos)+ getTamañoIntervalo());
        while(this.datosAgrupados.size()!= this.numeroIntervalos){
            this.datosAgrupados.get(i).setMin(datosAgrupados.get(i-1).getMax()+0.1);
            this.datosAgrupados.get(i).setMin(datosAgrupados.get(i).getMin() - 0.1+ getTamañoIntervalo());
            i++;
        }
        i = 1;
    }
    public void llenarIntervalos(){
        
        while(j<datosAgrupados.size()){
            while(w<datos.size()){
                if(datos.get(w)>datosAgrupados.get(j).getMin() && datos.get(w)<datosAgrupados.get(j).getMin()){
                    datosAgrupados.get(j).anadirDatos(datos.get(w));
                }
                w++;
            }
            j++;
        }
    }
    public void AnadirFrecuencias(){
    
    
    
    }
    
}
