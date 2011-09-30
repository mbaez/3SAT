/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.util;

import java.util.*;
import javax.swing.table.*;

/**
 * Modelo creado para una tabla dinamica que crece para abajo, se pueden agregar filas de forma dinamica
 * ya que utilizamos un ArrayList que es una lista enlazada del tipo generico<>
 * que se encuetra en el paquete java.util.*;
 * @author Mbaez
 */
public class EditableTableModel extends DefaultTableModel {

    private Object ref;
    private ArrayList<Object[]> data; //representa la lista de filas que tendremos
    private int nroColumnas; //numero de columnas
    private Class types[];    //nombre de las clases que se almacenar en la tabla
    private boolean editable[]; //si el campo es editable o no

    /**
     * Constructor de la Clase EditableTableModel
     * resive el nombre que van a tener las columnas, que tipo de dato albergaran, y si seran editables
     * @param nombreColumnas de las columnas de la tabla que seran visibles en la cabecera
     * @param clazz el tipo de datos de la tabla
     * @param editable indica que columnas son editables y cuales no
     */
    public EditableTableModel(String[] nombreColumnas, Class[] clazz, boolean[] editable) {
        initComponents(nombreColumnas, clazz, editable);
    }

    private void initComponents(String[] nombreColumnas, Class[] clazz, boolean[] editable)
            throws IllegalArgumentException {
        if (nombreColumnas.length != clazz.length || clazz.length != editable.length) {
            throw new IllegalArgumentException("El paso de parametros deben ser del mismo tamanho");
        }

        dataVector.add(ref);
        data = new ArrayList<Object[]>();
        ref = data;
        this.nroColumnas = nombreColumnas.length;
        this.setColumnIdentifiers(nombreColumnas);
        this.setRowCount(0);
        this.types = clazz;
        this.editable = editable;


    }

    public ArrayList<Object[]> get() {
        return data;
    }
    /*
     * Al heredar de DefaultTableModel y al agregar nuevos atributos significa que tendremos que redefinir varios metodos
     * para que la tabla pueda manejar nuestos datos.
     * los metods a redefinir son los sigts:
     * +Class getColumnClass(int columnIndex): que debe retornar que tipo de dato alberga la columna "columnIndex"
     * +boolean isCellEditable(int rowIndex, int columnIndex): debe retornar si la columna "columnIndex" puede ser editada
     * +void addRow(Object[] row):debe agregar el objeto "row" a una nueva columna
     * +void removeRow(int row):remueve la fila row
     * +Object getValueAt(int row, int column):retorna el valor almacenado en la fila "row" y la columna "column"
     * +void setValueAt(Object aValue, int rowIndex, int columnIndex):establece el objeto "aValue" de la tabla en la fila "rowIndex" y la columna "columnIndex"
     * 
     */

    /**
     *
     * @param columnIndex el numero de columnas
     * @return el tipo de dato de la columna indicada
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    /**
     *
     * @return el nombre de las columnas
     */
    public Object[] getColumnIdentifiers() {
        return columnIdentifiers.toArray();
    }

    /**
     *
     * @param rowIndex numero de fila
     * @param columnIndex numero de columna
     * @return true si es editable en caso contrario false
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable[columnIndex];
    }

    /**
     *
     * @param row anhade una fila en la tabla
     */
    @Override
    public void addRow(Object[] row) {
        int numRows = data.size();
        data.add(row);
        this.setRowCount(data.size());
        this.fireTableRowsInserted(numRows, numRows + 1);//actializamos el modelo
    }

    /**
     *
     * @param row indice de la fila a eliminar
     */
    @Override
    public void removeRow(int row) {
        data.remove(row);
        this.setRowCount(data.size());
        this.fireTableRowsDeleted(this.getRowCount(), this.getRowCount());//actializamos el modelo
    }

    /**
     *
     * @param row numero de fila
     * @param column numero de columna
     * @return el valor almacenado en dicha posicion
     */
    @Override
    public Object getValueAt(int row, int column) {
        Object rowVector[] = data.get(row);
        return rowVector[column];
    }

    /**
     *
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Object[] columna;
        columna = getValueAt(rowIndex);
        columna[columnIndex] = aValue;
        data.set(rowIndex, columna);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     *
     * @param row
     * @return Object []
     */
    public Object[] getValueAt(int row) {
        Object rowVector[] = data.get(row);
        return rowVector;
    }
    //podemos agregar de forma dinamica las filas con el metodo addRow

    /**
     *
     */
    public void addRow() {
        Object[] row = new Object[nroColumnas];
        addRow(row);
    }

    /**
     * 
     * @param rows
     */
    public void addRow(List<Object[]> rows) {
        for (Object[] row : rows) {
            addRow(row);
        }
    }

    /**
     * this method remove all row of the model
     */
    public void removeRow() {
        this.setRowCount(00);
        data.clear();
        this.fireTableRowsDeleted(0, this.getRowCount());
        this.setNumRows(0);
        this.nroColumnas = 0;
    }
}
