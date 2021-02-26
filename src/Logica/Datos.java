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
import java.lang.Object;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import static org.apache.commons.math3.stat.inference.TestUtils.kolmogorovSmirnovTest;

/**
 *
 * @author diego
 */
public class Datos {
    
    
    int i = 1;
    int j = 0;
    int w = 0;
    int u = 1;

    ArrayList<Double> datos;
    int numeroIntervalos = 0;
    int tamañoIntervalo = 0;
    ArrayList<Intervalo> datosAgrupados;
    double promedio= 0;
    double varianza=0;
    double desviacionEstandar;
    double chi1 =0;
    private double[] tabulado = new double [100]; 

    
    
    ChiSquareTest chi = new ChiSquareTest();
    KolmogorovSmirnovTest kolmo = new KolmogorovSmirnovTest();
    
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

    public void setTamañoIntervalo(int tamañoIntervalo) {
        this.tamañoIntervalo = tamañoIntervalo;
    }

    public ArrayList<Intervalo> getDatosAgrupados() {
        return datosAgrupados;
    }

    public void setDatosAgrupados(ArrayList<Intervalo> datosAgrupados) {
        this.datosAgrupados = datosAgrupados;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }

    public double getVarianza() {
        return varianza;
    }

    public void setVarianza(double varianza) {
        this.varianza = varianza;
    }

    public double getChi1() {
        return chi1;
    }

    public void setChi1(double chi1) {
        this.chi1 = chi1;
    }

    
    
    

    public void anadirDatos(Double i) {
        getDatos().add(i);
    }

    public void calcularnumeroDeIntervalo() {
        double h = (1+(3.3)*Math.log10(getDatos().size()))+1;
        double s = Math.floor(h);
        setNumeroIntervalos((int) Math.floor(s));
    }

    public void calcularTamañoIntervalo() {
        double h= Math.floor(((Collections.max(datos) - Collections.min(datos)) / this.numeroIntervalos)+1);
        setTamañoIntervalo((int) h);
    }

    public void crearIntevalos() {
        Collections.sort(datos);
        Intervalo inter = new Intervalo();
        inter.setMin(datos.get(0));
        inter.setMax(datos.get(0) + getTamañoIntervalo());
        getDatosAgrupados().add(inter);
        while (this.datosAgrupados.size() != this.numeroIntervalos) {
            Intervalo inter1 = new Intervalo();
            inter1.setMin(datosAgrupados.get(i - 1).getMax() + 0.1);
            inter1.setMax(inter1.getMin() - 0.1 + getTamañoIntervalo());
            getDatosAgrupados().add(inter1);
            i++;
        }
        i = 1;
    }

    public void llenarIntervalos() {

        while (j < datosAgrupados.size()) {
            ArrayList<Double> agrupados = new ArrayList();
            while (w < datos.size()) {
                if (datos.get(w) >= datosAgrupados.get(j).getMin() && datos.get(w) <= datosAgrupados.get(j).getMax()) {
                    agrupados.add(datos.get(w));
                }
                w++;
            }
            datosAgrupados.get(j).setDatosIntervalo(agrupados);
            w=0;
            j++;
        }
        j = 0;
        w = 0;
    }

    public void AnadirFrecuencias() {
        
        double numero = getNumeroIntervalos();
        
        double expected[] = new double[datosAgrupados.size()];
        long observed[] = new long[datosAgrupados.size()];
        
        double POA[] = new double[datosAgrupados.size()];
        double PEA[] =  new double[datosAgrupados.size()];     
        
        
        datosAgrupados.get(0).calcularFrecuenciaAbsoluta();
        datosAgrupados.get(0).setFrecuenciaAbsolutaAcumulada(datosAgrupados.get(0).getFrecuenciaAbsoluta());
        datosAgrupados.get(0).setFrecuenciaRelativa(datosAgrupados.get(0).getFrecuenciaAbsoluta() / datos.size());
        datosAgrupados.get(0).setFrecuenciaRelativaAcumulada(datosAgrupados.get(0).getFrecuenciaAbsoluta() / datos.size());
        datosAgrupados.get(0).setFrecuenciaEsperada(1/numero);
        datosAgrupados.get(0).setFrecuenciaEsperadaAcumulada(1/numero);
        datosAgrupados.get(0).setChi2(Math.pow((datosAgrupados.get(0).getFrecuenciaAbsoluta()-datosAgrupados.get(0).getFrecuenciaEsperada() ), 2)/datosAgrupados.get(0).getFrecuenciaEsperada());
        
        expected[0] = datosAgrupados.get(0).getFrecuenciaEsperada();
        observed[0] = (long) datosAgrupados.get(0).frecuenciaRelativa;
        
        
        
        while (u < datosAgrupados.size()) {
            
            datosAgrupados.get(u).calcularFrecuenciaAbsoluta();
            
            datosAgrupados.get(u).setFrecuenciaAbsolutaAcumulada(datosAgrupados.get(u - 1).getFrecuenciaAbsolutaAcumulada() + datosAgrupados.get(u).getFrecuenciaAbsoluta());
            
            datosAgrupados.get(u).setFrecuenciaRelativa(datosAgrupados.get(u).getFrecuenciaAbsoluta() / datos.size());
            
            datosAgrupados.get(u).setFrecuenciaRelativaAcumulada(datosAgrupados.get(u - 1).getFrecuenciaRelativaAcumulada() + datosAgrupados.get(u).getFrecuenciaRelativa());
            datosAgrupados.get(u).setFrecuenciaEsperada(1/numero);
            datosAgrupados.get(u).setFrecuenciaEsperadaAcumulada(this.datosAgrupados.get(u - 1).getFrecuenciaEsperadaAcumulada() + this.datosAgrupados.get(u).getFrecuenciaEsperada());
            
            datosAgrupados.get(u).setChi2(Math.pow((datosAgrupados.get(u).getFrecuenciaAbsoluta()-datosAgrupados.get(u).getFrecuenciaEsperada() ), 2)/datosAgrupados.get(u).getFrecuenciaEsperada());
            
            expected[u] = datosAgrupados.get(u).getFrecuenciaEsperada();
            observed[u] = (long) datosAgrupados.get(u).getFrecuenciaRelativa();
            
            POA[u] = datosAgrupados.get(u).getFrecuenciaRelativaAcumulada();
            PEA[u] = datosAgrupados.get(u).getFrecuenciaEsperadaAcumulada();
            
            chi1= chi1 + datosAgrupados.get(u).getChi2();
            
            u++;
        }
         
        u = 1;
    }

    public void imprimirFrecuencias() {
        while (j < datosAgrupados.size()) {
            System.out.println("(" + datosAgrupados.get(j).getMin() + "-" + datosAgrupados.get(j).getMax() + ")"
                    + " fi = " + datosAgrupados.get(j).getFrecuenciaAbsoluta()
                    + " Fi = " + datosAgrupados.get(j).getFrecuenciaAbsolutaAcumulada()
                    + " fi/n = " + datosAgrupados.get(j).getFrecuenciaRelativa()
                    + " Fi/n = " + datosAgrupados.get(j).getFrecuenciaRelativaAcumulada()
                    + " PE = " + datosAgrupados.get(j).getFrecuenciaEsperada()
                    + " PEA = " + datosAgrupados.get(j).getFrecuenciaEsperadaAcumulada()
                    + " chi2 = " + datosAgrupados.get(j).getChi2() + "\n");
                    
            j++;
        }
    }
    public void calcularPromedio(){
        double media=0;
        for(Double temp: datos){
            media = media+temp;
        }
        setPromedio(media/getDatos().size());
    }
    public void calcularDesviacionEstandarYVarianza(){
        calcularPromedio();
        double desviacion=0;
        for(Double temp: datos){
            desviacion = desviacion + Math.pow(temp-getPromedio(),2);
        }
        setVarianza(desviacion/getDatos().size());
        setDesviacionEstandar(Math.sqrt(getVarianza()));
        System.out.println("varianza: "+ getVarianza()+"\n");
        System.out.println("Desciacion estandar: "+ getDesviacionEstandar()+"\n");
    }
    public void reultadoChiCuadrado(){
        System.out.println("chi2 => "+ chi1+"\n");
        System.out.println("test chi2 => "+ testChiCuadrado()+"\n");
    } 
    public boolean testChiCuadrado()
    {
        if (numeroIntervalos > 1)
        {
            chiCuadrado();
            if (tabulado[numeroIntervalos - 2] > chi1)  //cantidadIntervalos - 1 = k - 1 = V (grados de libertad)
            {
                return true;
            }
        }
        return false;
    }
    
    public void chiCuadrado()
    {
        this.tabulado[0] = 3.841;
        this.tabulado[1] = 5.991;
        this.tabulado[2] = 7.815;
        this.tabulado[3] = 9.488;
        this.tabulado[4] = 11.070;
        this.tabulado[5] = 12.592;
        this.tabulado[6] = 14.067;
        this.tabulado[7] = 15.507;
        this.tabulado[8] = 16.919;
        this.tabulado[9] = 18.307;
        this.tabulado[10] = 19.675;
        this.tabulado[11] = 21.026;
        this.tabulado[12] = 22.362;
        this.tabulado[13] = 23.685;
        this.tabulado[14] = 24.996;
        this.tabulado[15] = 26.296;
        this.tabulado[16] = 27.587;
        this.tabulado[17] = 28.869;
        this.tabulado[18] = 30.144;
        this.tabulado[19] = 31.410;
        this.tabulado[20] = 32.671;
        this.tabulado[21] = 33.924;
        this.tabulado[22] = 35.172;
        this.tabulado[23] = 36.415;
        this.tabulado[24] = 37.652;
        this.tabulado[25] = 38.885;
        this.tabulado[26] = 40.113;
        this.tabulado[27] = 41.337;
        this.tabulado[28] = 42.557;
        this.tabulado[29] = 43.773;
        this.tabulado[39] = 53.7;
        this.tabulado[49] = 66.8;
        this.tabulado[59] = 79.5;
        this.tabulado[69] = 104.2;
        this.tabulado[79] = 116.3;
        this.tabulado[89] = 128.3;
        this.tabulado[99] = 140.2;
    }    
}
