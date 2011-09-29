/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.ia.sat;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class Variable {
    private String id;
    private boolean notValue;
    private boolean value;
    /**
     * 
     * @param termino
     */
    public Variable(String termino){
        procesar(termino);
    }
    /**
     *
     * @return
     */
    public String getId(){
        return id;
    }
    /**
     * 
     * @return
     */
    public boolean getNotValue(){
        return notValue;
    }
    /**
     *
     * @return
     */
    public boolean getValue(){
        return value;
    }
    /**
     *
     * @param termino
     */
    private void procesar(String termino){
        if(termino.contains("!")){
            notValue = false;
        }else{
            notValue = true;
        }
        id = termino.replace("!", "");

        //System.out.println(notValue+" " + id);
    }
    /**
     * 
     * @return
     */
    public boolean evaluar(){

        return notValue && value;
    }
    /**
     * 
     * @param id
     * @param value
     */
    public void setValueFor(String id, boolean value){
        if(this.id.equals(id)){
            this.value =  value;
        }
    }

}
