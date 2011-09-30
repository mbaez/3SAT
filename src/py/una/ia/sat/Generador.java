/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.ia.sat;

import java.util.ArrayList;
/**
 *
 * @author Luis
 */


public class Generador {
    private int nroVariables;
    private int nroClausulas;
    private String expresion;
    private ArrayList<String> listaVar;

    public Generador() {
        nroVariables = 0;
        nroClausulas = 0;
        expresion = null;
    }

    public Generador(int variables, int clausulas) {
        nroVariables = variables;
        nroClausulas = clausulas;
        expresion = "";
    }

    public ArrayList<String> getArrayVariables(){
        return this.listaVar;

    }

    public ArrayList<String> generarVariables() {
        ArrayList<String> listaVariables = new ArrayList<String>(nroClausulas * 3);

        for(int i=0; i < (nroClausulas * 3); i++) {
            listaVariables.add("x" + String.valueOf((int) (Math.random() * nroVariables)));;
        }

        return listaVariables;
    }

    public String generarExpresion() {
        int contVariables = 0;
        expresion= "";
        listaVar = generarVariables();
        for(int i = 0; i < nroClausulas; i++) {

            String clausula = "(";

            for (int j = contVariables; j < contVariables + 3; j++) {

                if(Math.random() * 100 < 50) {
                    clausula = clausula + "!";
                }
                clausula = clausula + listaVar.get(j);
                if (j < contVariables + 2)
                clausula = clausula + "v";
            }

            clausula += ")";
            contVariables +=3;
            expresion += clausula;

            if(i < nroClausulas - 1)
                expresion += "^";
        }

        return expresion;
    }
}

