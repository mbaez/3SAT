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
        int N = 100;
        int variables = 40;
        for (int clausulas = 1; clausulas <= 100; clausulas += 1) {
            //for (int variables = 10; variables <= 3*clausulas; variables += 5) {
                Generador gen = new Generador(variables, clausulas);
                double backTime = 0.0, mvrTime = 0.0, vegasTime = 0.0;
                int backNodos = 0,vegasNodos = 0 , mvrNodos = 0;
                double  cantVariables=0.0;
                String expresion ="";
                 int backSolu = 0,vegasSolu = 0 , mvrSolu = 0;

                Problema p1 = null,p2= null, p3= null;
                for (int i = 0; i < N; i++) {
                    expresion = gen.generarExpresion();
                    p1 = new Problema(expresion);
                    p2 = new Problema(expresion);
                    p3 = new Problema(expresion);

                    long start = System.currentTimeMillis();
                    p1.ResolverBacktracking();

                    long endBack = System.currentTimeMillis();
                    p2.ResolverLasVegas();

                    long endVegas = System.currentTimeMillis();
                    p3.ResolverMVR();
                    long endMVR = System.currentTimeMillis();

                    backTime += endBack-start;
                    vegasTime += endVegas-endBack;
                    mvrTime += endMVR - endVegas;
                    
                    backNodos+=p1.getNodosExpandidos();
                    vegasNodos+=p2.getNodosExpandidos();
                    mvrNodos+=p3.getNodosExpandidos();
                    cantVariables += p1.getExpresion().getVariables().size();

                    backSolu+=p1.getSoluciones().soluciones.size();
                    vegasSolu+=p2.getSoluciones().soluciones.size();
                    mvrSolu+=p3.getSoluciones().soluciones.size();
                }
                //p.printSoluciones();
                backTime = backTime/(N*1.0);
                vegasTime = vegasTime/(N*1.0);
                mvrTime = mvrTime/(N*1.0);

                System.out.println("\n============================================");
                System.out.println("Clausulas:"+clausulas+"\nVariables: "+(int)cantVariables/N);
                //System.out.println("Exresion: "+ expresion);
                System.out.println("Razon: "+ clausulas*1.0/(variables*1.0));
                System.out.println("Algo :\tBT ms.\t\tLV ms.\t\tMVR ms.");
                System.out.println("Time :\t" +backTime+  " ms.\t\t"+vegasTime+ " ms.\t\t"+mvrTime+ "ms.");
                System.out.println("Nodos:\t" +(int)backNodos/N+ "    \t\t"+(int)vegasNodos/N+"    \t\t"+(int)mvrNodos/N+ "");
                System.out.println("Solu :\t" +(int)backSolu*100/N+ "%   \t\t"+(int)vegasSolu*100/N+"%   \t\t"+(int)mvrSolu*100/N+ "%");
            //}
        }

    }
}
