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
public class Conjuncion {
    private ArrayList<Variable> terminos;
    
    /**
     *
     * @param conjuncion
     */
    public Conjuncion(String conjuncion){
        terminos = new ArrayList<Variable>();
        procesar(conjuncion);
    }
    public void setValueFor(String id, boolean value){
        for(Variable t : terminos){
            t.setValueFor(id, value);
        }
    }
    /**
     * 
     * @param conjuncion
     */
    private void procesar(String conjuncion){
        conjuncion = conjuncion.replace("(","").replace(")","");
        StringTokenizer  tokens  = new StringTokenizer(conjuncion, "v");
        
        while(tokens.hasMoreTokens()){
            terminos.add(new Variable(tokens.nextToken()));
        }
    }
    /**
     *
     * @return
     */
    public boolean evaluar(){
        boolean resp = false;
        for(Variable t : terminos){
            if (t.evaluar())
                resp= true;
            //System.out.print(" v ");
        }
        return resp;
    }
    public ArrayList<Variable> getTerminos(){
        return terminos;
    }
    
    /**
     * 
     * @return
     */
    public HashMap<String, Boolean> getTerminosHashMap(){
        HashMap<String, Boolean> tablaVariables = new HashMap<String, Boolean>();
         for(Variable t : terminos){
            tablaVariables.put(t.getId(),null);
        }
        return tablaVariables;
    }
    
}
