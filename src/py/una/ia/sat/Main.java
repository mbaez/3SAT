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
        Problema  p = new Problema("(xvyvz)^(!xv!yv!z)");
        p.ResolverBacktracking();
        p.printSoluciones();

    }


}
