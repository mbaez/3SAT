/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.ia.sat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class Solucion {

    protected Expresion expresion;
    protected ArrayList<HashMap<String, Boolean>> soluciones;
    protected HashMap<String, Boolean> asignadas;
    protected boolean[] dominioVariable = {true, false};
    protected int nodos=0;

    /**
     *
     */
    public Solucion() {
        soluciones = new ArrayList<HashMap<String, Boolean>>();
        asignadas = new HashMap<String, Boolean>();
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }
    public int getNodos(){
        return nodos;
    }

    /**
     *
     * @param asignadas
     * @return
     */
    public boolean backtracking(HashMap<String, Boolean> asignadas) {
        //System.out.println("Asignacion: "+asignadas + "\tCompletitud:"+esCompleto(asignadas));
        nodos++;
        if (esCompleto(asignadas) && expresion.evaluar()) {

            HashMap<String, Boolean> solucion = new HashMap<String, Boolean>();
            solucion.putAll(asignadas);
            this.soluciones.add(solucion);
            return true;
        }
        /*else if(nodos > (expresion.getVariables().size()*expresion.getConjunciones().size())*1000)
            return true;
        */
        String seleccionada = seleccionarVariableNoAsignada(asignadas);

        if (seleccionada == null) {
            return false;
        }
        for (boolean value : dominioVariable) {
            expresion.setTableValueAt(seleccionada, value);
            asignadas.put(seleccionada, value);

            //System.out.println("Seleccionada: "+ seleccionada + "\t Value:"+value);

            HashMap<String, Boolean> newAsignacion = new HashMap<String, Boolean>();
            newAsignacion.putAll(asignadas);

            boolean resultado = backtracking(newAsignacion);

            if (resultado) {
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
    public String seleccionarVariableNoAsignada(HashMap<String, Boolean> asignadas) {

        for (Object variable : expresion.getVariables().keySet()) {
            if (!asignadas.containsKey((String) variable)) {
                return (String) variable;
            }
        }

        return null;
    }

    /**
     *
     * @return
     */
    private boolean esCompleto(HashMap<String, Boolean> asignadas) {
        //System.out.println("EVAL:"+ expresion.evaluar());
        //System.out.println("\n");
        boolean resp = asignadas.size() == expresion.getVariables().size();
        // System.out.println("\n");
        return resp;
    }

    public void printSoluciones() {
        int i = 1;
        for (HashMap<String, Boolean> solucion : soluciones) {
            System.out.println("===========Solucion #" + i + "===============");
            for (String id : solucion.keySet()) {
                System.out.println("{" + id + " = " + solucion.get(id) + "}");
            }
            i++;

        }
        System.out.println("=====================================");
    }
}
