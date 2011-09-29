/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.ia.sat;

import java.util.HashMap;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class SolucionMVR extends Solucion {
    private int K = 10;
    public SolucionMVR() {
        super();
    }

    /**
     * 
     * @param asignadas
     * @return
     */
    @Override
    public String seleccionarVariableNoAsignada(HashMap<String, Boolean> asignadas) {



        for (Object variable : expresion.getVariables().keySet()) {
            if (!asignadas.containsKey((String) variable)) {
                return (String) variable;
            }
        }

        return null;
    }

    private int calcularProbabilidad(){


        return 0;
    }

    private int ocurrenciaClausulasNoSatisfechas(String id, Boolean negado){

        return 0;
    }
}
