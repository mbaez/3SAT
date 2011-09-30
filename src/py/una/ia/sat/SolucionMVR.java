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
        int maxValue = 0;
        String maxVariable = null;
        
        for (Object variable : expresion.getVariables().keySet()) {
            if (!asignadas.containsKey(variable)) {

                int actualValue = calcularValor((String) variable);
                if (maxValue < actualValue) {

                    maxValue = actualValue;
                    maxVariable = (String) variable;

                } else if (maxValue == actualValue) {
                    //TODO Seleccionar randomicamente el valor
                    maxValue = actualValue;
                    maxVariable = (String) variable;
                }
            }
        }

        return maxVariable;
    }

    private int calcularValor(String id) {

        int[] ocurrencias = this.ocurrenciaClausulasNoSatisfechas(id);
        int fx = ocurrencias[0];
        int fxNegada = ocurrencias[1];

        return (fx + fxNegada) * 2 ^ K + fx * fxNegada;
    }

    /**
     * 
     * @param id
     * @return
     */
    private int[] ocurrenciaClausulasNoSatisfechas(String id) {
        int ocurrencias = 0;
        int ocurrenciasNegadas = 0;

        for (Conjuncion c : expresion.getConjunciones()) {
            if (!c.evaluar()) {
                for (Variable t : c.getTerminos()) {

                    if (t.getId().equals(id)) {
                        if (t.getNegado()) {
                            ocurrenciasNegadas++;
                        } else {
                            ocurrencias++;
                        }
                    }
                }
            }
        }
        int[] totalOcurrencias = {ocurrencias, ocurrenciasNegadas};
        return totalOcurrencias;
    }
}
