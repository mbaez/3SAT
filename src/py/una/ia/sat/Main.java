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

        for (int clausulas = 5; clausulas <= 100; clausulas += 5) {
            for (int variables = 5; variables <= 3*clausulas; variables += 5) {
                Generador gen = new Generador(variables, clausulas);
                long backTime = 0;
                long vegasTime = 0;
                long mvrTime = 0;
                for (int i = 0; i < 10; i++) {
                    Problema p = new Problema(gen.generarExpresion());
                    long start = System.currentTimeMillis();
                    p.ResolverBacktracking();
                    long endBack = System.currentTimeMillis();
                    p.ResolverLasVegas();
                    long endVegas = System.currentTimeMillis();
                    p.ResolverMVR();
                    long endMVR = System.currentTimeMillis();

                    backTime+=endBack-start;
                    vegasTime+=endVegas-endBack;
                    mvrTime+=endMVR - endVegas;
                }
                //p.printSoluciones();
                System.out.println("\n============================================");
                System.out.println("Clausulas:"+clausulas+"\nVariables: "+variables);
                System.out.println("Algo:\tBT ms.\tLV ms.\tMVR ms.");
                System.out.println("Time:\t" +backTime/10+ " ms.\t"+vegasTime/10+" ms.\t"+mvrTime/10+ "ms.");
            }
        }

    }
}
