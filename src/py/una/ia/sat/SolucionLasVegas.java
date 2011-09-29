/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.ia.sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class SolucionLasVegas extends Solucion{
    public SolucionLasVegas() {
        super();
    }

    /**
     *
     * @param asignadas
     * @return
     */

    @Override
    public String seleccionarVariableNoAsignada(HashMap<String, Boolean> asignadas) {
        ArrayList<String> noAsignadas = new ArrayList<String>();

        for (Object variable : expresion.getVariables().keySet()) {
            if (!asignadas.containsKey((String) variable)) {
                noAsignadas.add((String)variable);
            }
        }
        int length = noAsignadas.size();

        if (length <= 0)
            return null;

        Random rnd = new Random();
        int index = rnd.nextInt(length);

        return noAsignadas.get(index);

    }
}
