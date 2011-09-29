/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.ia.sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class Expresion {

    private ArrayList<Conjuncion> conjunciones;
    private HashMap <String, Boolean> variables;
    
    /**
     * 
     * @param expresion
     */
    public Expresion(String expresion){
        conjunciones = new ArrayList<Conjuncion>();
        variables = new HashMap<String, Boolean>();
        procesar(expresion);
        initTablaVariables();
        System.out.println("Expresion: "+ expresion);
    }
    /**
     * 
     * @return
     */
    public HashMap getVariables(){
        return variables;
    }
    /**
     * 
     * @return
     */
    public ArrayList<Conjuncion> getConjunciones(){
        return conjunciones;
    }

    /**
     * 
     */
    public void printTabla(){
        for(Object o : variables.keySet()){
            System.out.println("{"+o+" : "+variables.get(o)+"}");
        }
    }
    /**
     *
     * @param id
     * @param value
     */
    public void setTableValueAt(String id,boolean value){
        variables.put(id, value);
        for(Conjuncion c : conjunciones){
            c.setValueFor(id, value);
        }
    }

    /**
     *
     * @param expresion
     */
    private void procesar(String expresion){
        expresion = expresion.replaceAll(" ","");
        StringTokenizer tokens = new StringTokenizer(expresion, "^");

        while(tokens.hasMoreTokens()){
            conjunciones.add(new Conjuncion(tokens.nextToken()));
        }
    }
    /**
     *
     * @return
     */
    public boolean evaluar(){
        boolean resp = true;
        for(Conjuncion c : conjunciones){
            if (!c.evaluar())
                resp = false;
            //System.out.print(") ^ (");
        }

        return resp;
    }
    
    /**
     * 
     */
    private void initTablaVariables(){

        for(Conjuncion c : conjunciones){
            variables.putAll(c.getTerminos());
        }

        System.out.println("Variables: "+ variables.keySet());
    }

}
