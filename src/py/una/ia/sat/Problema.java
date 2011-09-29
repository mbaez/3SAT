package py.una.ia.sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
    private HashMap<String, Boolean> soluciones;

    public Problema( String expresionString){
        expresion = new Expresion(expresionString);
        soluciones =  new HashMap<String, Boolean>();
    }
    
    /**
     * 
     * @return
     */
    public boolean ResolverBacktracking(){
        //TODO 
        ArrayList<Conjuncion> conjunciones = expresion.getConjunciones();
        Set<String> keys = expresion.getVariables().keySet();
        return expresion.evaluar();
    }
    

}
