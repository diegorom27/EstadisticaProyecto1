/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticaProyecto;

import Logica.Datos;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class EstadisticaProyecto {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 1;
        
        while (opcion != 0) {
            System.out.println("1. -----Introducir datos -------\n");
            System.out.println("2. -----Crear intervalos --------\n");
            System.out.println("3. -----Cacular frecuencias -----\n");
            System.out.println("5. -----Mostrar frecuencias-------\n");
            System.out.println("5.-------------salir-------------\n");

            Datos datos = new Datos();
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    double dato = sc.nextDouble();
                    datos.anadirDatos(dato);
                    break;
                case 2:
                    datos.calcularnumeroDeIntervalo();
                    datos.calcularTamañoIntervalo();
                    datos.crearIntevalos();
                    datos.llenarIntervalos();
                    break;
                case 3:
                    datos.AnadirFrecuencias();
                break;
                case 4:
                    datos.imprimirFrecuencias();
                break;
                case 5:
                    opcion = 0;
                break;

            }
        }
    }

}
