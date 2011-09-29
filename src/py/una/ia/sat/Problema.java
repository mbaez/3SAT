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
    private ArrayList<HashMap<String, Boolean>> soluciones;
    HashMap<String, Boolean> asignadas;
    private boolean[] dominioVariable = {true,false};

    /**
     * 
     * @param expresionString
     */
    public Problema( String expresionString){
        expresion = new Expresion(expresionString);
        soluciones = new ArrayList<HashMap<String, Boolean>>();
        asignadas = new HashMap<String, Boolean>();
    }
    /**
     *
     * @return
     */
    public Expresion getExpresion(){
        return expresion;
    }
    /**
     *
     * @return
     */
    public ArrayList<HashMap<String, Boolean>> getSoluciones(){
        return this.soluciones;
    }
    /**
     *
     */
    public void printSoluciones(){
        int i = 1;
        for (HashMap<String, Boolean> solucion: soluciones){
            System.out.println("===========Solucion #"+i+"===============");
            for (String id : solucion.keySet()){
                System.out.println("{"+id+" = "+solucion.get(id)+"}");
            }
            i++;
            
        }
        System.out.println("=====================================");
    }
    
    /**
     * 
     * @return
     */
    public boolean ResolverBacktracking(){
        
        return backtracking(asignadas);
    }
    /**
     *
     * @param asignadas
     * @return
     */
    private boolean backtracking( HashMap<String, Boolean> asignadas){
        //System.out.println("Asignacion: "+asignadas + "\tCompletitud:"+esCompleto(asignadas));
        if(esCompleto(asignadas)){

            HashMap<String, Boolean> solucion = new HashMap<String, Boolean>();
            solucion.putAll(asignadas);
            this.soluciones.add(solucion);
            return false;
        }

        String seleccionada = seleccionarVariableNoAsignada(asignadas);

        if(seleccionada == null)
            return false;
        for (boolean value : dominioVariable){
            getExpresion().setTableValueAt(seleccionada, value);
            asignadas.put(seleccionada, value);

            //System.out.println("Seleccionada: "+ seleccionada + "\t Value:"+value);

            HashMap<String, Boolean> newAsignacion = new HashMap<String, Boolean>();
            newAsignacion.putAll(asignadas);
            
            boolean resultado = backtracking(newAsignacion);

            if(resultado){
                //System.out.println("Result "+resultado);
                return true;
            }

        }
        return false;
    }

    /**
     * 
     * @param asignadas
     * @return
     */
    private String seleccionarVariableNoAsignada(HashMap<String, Boolean> asignadas){

        for(Object variable : expresion.getVariables().keySet()){
            if(!asignadas.containsKey((String)variable))

                return (String)variable;
        }

        return null;
    }
    /**
     * 
     * @return
     */
    private boolean esCompleto(HashMap<String, Boolean> asignadas){
        //System.out.println("EVAL:"+ expresion.evaluar());
        //System.out.println("\n");
        boolean resp =  asignadas.size() == expresion.getVariables().size()
                && expresion.evaluar();
       // System.out.println("\n");
        return resp;
    }


}
