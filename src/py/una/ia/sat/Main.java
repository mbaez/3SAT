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
        Generador gen = new Generador(100, 20);
        Problema  p = new Problema(gen.generarExpresion(), Problema.MVR);
        long start = System.currentTimeMillis();
        p.ResolverBacktracking();
        long end = System.currentTimeMillis();
        //p.printSoluciones();
        System.out.println("Time: "+ (end-start) + " ms." );

    }


}
