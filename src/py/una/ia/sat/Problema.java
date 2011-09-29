package py.una.ia.sat;

import java.util.ArrayList;
import java.util.HashMap;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class Problema {

    private Expresion expresion;
    private Solucion solucion;
    public static int LASVEGAS = 1;
    public static int BACKTRACKING = 2;
    public static int MVR = 3;

    /**
     * 
     * @param expresionString
     */
    public Problema(String expresionString, int algoritmo) {
        expresion = new Expresion(expresionString);
        if (LASVEGAS == algoritmo) {
            solucion = new SolucionLasVegas();

        } else if (this.BACKTRACKING == algoritmo) {
            solucion = new Solucion();

        } else if (this.MVR == algoritmo) {
            solucion = new SolucionMVR();            
        }
        
        solucion.setExpresion(expresion);
        System.out.println("Expresion : "+expresionString);

    }

    /**
     *
     * @return
     */
    public Expresion getExpresion() {
        return expresion;
    }

    public void printSoluciones() {
        solucion.printSoluciones();
    }

    /**
     * 
     * @return
     */
    public boolean ResolverBacktracking() {
        return solucion.backtracking(new HashMap<String, Boolean>());
    }
}
