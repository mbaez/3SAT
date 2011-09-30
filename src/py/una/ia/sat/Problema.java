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
    public Problema(String expresionString) {
        expresion = new Expresion(expresionString);
        //System.out.println("Expresion : "+expresionString);

    }
    public int getNodosExpandidos(){
        return solucion.getNodos();
    }
    public Solucion getSoluciones(){
        return solucion;
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
        solucion = new Solucion();
        solucion.setExpresion(expresion);
        return solucion.backtracking(new HashMap<String, Boolean>());
    }

    public boolean ResolverLasVegas() {
        solucion = new SolucionLasVegas();
        solucion.setExpresion(expresion);
        return solucion.backtracking(new HashMap<String, Boolean>());
    }
    public boolean ResolverMVR() {
        solucion = new SolucionMVR();
        solucion.setExpresion(expresion);
        return solucion.backtracking(new HashMap<String, Boolean>());
    }
}
