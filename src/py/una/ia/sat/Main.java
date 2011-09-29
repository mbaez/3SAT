/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.ia.sat;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Expresion c = new Expresion("(xvy)^(xv!y)^(xvy)^(!xvp)");
        for(Object id : c.getVariables().keySet()){
            c.setTableValueAt((String)id, true);
        }
        c.printTabla();
        System.out.println("Valor: "+ c.evaluar());

    }


}
