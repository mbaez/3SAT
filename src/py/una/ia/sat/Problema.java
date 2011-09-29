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

    /**
     * 
     * @param expresionString
     */
    public Problema( String expresionString){
        expresion = new Expresion(expresionString);
        solucion = new Solucion();
        solucion.setExpresion(expresion);

    }

    /**
     *
     * @return
     */
    public Expresion getExpresion(){
        return expresion;
    }

    public void printSoluciones(){
        solucion.printSoluciones();
    }
    
    /**
     * 
     * @return
     */
    public boolean ResolverBacktracking(){
        return solucion.backtracking(new HashMap<String, Boolean>());
    }

}
