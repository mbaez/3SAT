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
        int N = 5;
        int variables = 40;
        for (int clausulas = 5; clausulas <= 100; clausulas += 5) {
            //for (int variables = 10; variables <= 3*clausulas; variables += 5) {
                Generador gen = new Generador(variables, clausulas);
                long backTime = 0;
                long backNodos = 0;
                long vegasTime = 0;
                long vegasNodos = 0;
                long mvrTime = 0;
                long mvrNodos = 0;
                for (int i = 0; i < N; i++) {
                    String expresion = gen.generarExpresion();
                    Problema p1 = new Problema(expresion);
                    Problema p2 = new Problema(expresion);
                    Problema p3 = new Problema(expresion);
                    long start = System.currentTimeMillis();
                    p1.ResolverBacktracking();
                    long endBack = System.currentTimeMillis();
                    p2.ResolverLasVegas();
                    long endVegas = System.currentTimeMillis();
                    p3.ResolverMVR();
                    long endMVR = System.currentTimeMillis();

                    backTime+=endBack-start;
                    vegasTime+=endVegas-endBack;
                    mvrTime+=endMVR - endVegas;
                    
                    backNodos+=p1.getNodosExpandidos();
                    vegasNodos+=p2.getNodosExpandidos();
                    mvrNodos+=p3.getNodosExpandidos();
                }
                //p.printSoluciones();
                System.out.println("\n============================================");
                System.out.println("Clausulas:"+clausulas+"\nVariables: "+variables);
                System.out.println("Algo :\tBT ms.\tLV ms.\tMVR ms.");
                System.out.println("Time :\t" +backTime/N+ " ms.\t\t"+vegasTime/N+" ms.\t\t"+mvrTime/N+ "ms.");
                System.out.println("Nodos:\t" +backNodos/N+ "\t\t"+vegasNodos/N+"\t\t"+mvrNodos/N+ "");
            //}
        }

    }
}
