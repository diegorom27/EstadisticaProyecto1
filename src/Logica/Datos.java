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
    int u=1;
    
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

    public ArrayList<Intervalo> getDatosAgrupados() {
        return datosAgrupados;
    }

    public void setDatosAgrupados(ArrayList<Intervalo> datosAgrupados) {
        this.datosAgrupados = datosAgrupados;
    }
    
    
    
    public void anadirDatos(Double i) {
        getDatos().add(i);
    }
    
    public void calcularnumeroDeIntervalo(){
        setNumeroIntervalos( (int) (1+3.3*log(getDatos().size()+0.5)));
    }
    
    public void calcularTamañoIntervalo(){
        setTamañoIntervalo((Collections.max(datos)-Collections.min(datos))/getNumeroIntervalos());
    }

    public void crearIntevalos(){
        Collections.sort(datos);
        getDatosAgrupados().get(0).setMin(datos.get(0));
        getDatosAgrupados().get(0).setMax(datos.get(0)+ getTamañoIntervalo());
        while(this.datosAgrupados.size()!= this.numeroIntervalos){
            getDatosAgrupados().get(i).setMin(datosAgrupados.get(i-1).getMax()+0.1);
            getDatosAgrupados().get(i).setMin(datosAgrupados.get(i).getMin() - 0.1+ getTamañoIntervalo());
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
        j=0;
        w=0;
    }
    public void AnadirFrecuencias(){
        datosAgrupados.get(0).calcularFrecuenciaAbsoluta();
        datosAgrupados.get(0).setFrecuenciaAbsolutaAcumulada(datosAgrupados.get(0).getFrecuenciaAbsoluta());
        datosAgrupados.get(0).setFrecuenciaRelativa(datosAgrupados.get(0).getFrecuenciaAbsoluta()/datos.size());
        while(u<datosAgrupados.size()){
            datosAgrupados.get(u).calcularFrecuenciaAbsoluta();
            datosAgrupados.get(u).setFrecuenciaAbsolutaAcumulada(datosAgrupados.get(u-1).getFrecuenciaAbsolutaAcumulada() + datosAgrupados.get(u).getFrecuenciaAbsoluta());
            datosAgrupados.get(u).setFrecuenciaRelativa(datosAgrupados.get(u).getFrecuenciaAbsoluta()/datos.size());
            datosAgrupados.get(u).setFrecuenciaRelativaAcumulada(datosAgrupados.get(u-1).getFrecuenciaRelativaAcumulada() + datosAgrupados.get(u).getFrecuenciaRelativa());
        }
        u=1;
    }
    public void imprimirFrecuencias(){
        while(j<datosAgrupados.size()){
            System.out.println("("+datosAgrupados.get(j).getMin()+"-"+ datosAgrupados.get(j).getMin()+")"+ 
                    "fi = "+datosAgrupados.get(j).getFrecuenciaAbsoluta()+
                    "Fi = "+datosAgrupados.get(j).getFrecuenciaAbsolutaAcumulada()+
                    "fi/n = "+datosAgrupados.get(j).getFrecuenciaRelativa()+
                    "Fi/n = "+datosAgrupados.get(j).getFrecuenciaRelativaAcumulada()+"\n");
        }
    }
    
}
