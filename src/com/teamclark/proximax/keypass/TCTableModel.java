/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamclark.proximax.keypass;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author JunS
 */
public class TCTableModel extends AbstractTableModel {

    private ArrayList data = new ArrayList();
    private String[] columnNames = {"ERROR"};

    public TCTableModel(String[] cNames) {
        if (cNames != null) {
            columnNames = cNames;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        ArrayList colArrayList = (ArrayList) data.get(row);

        return colArrayList.get(col);
    }

    @Override
    public Class getColumnClass(int col) {
        // If value at given cell is null, return default class-String
        return getRowCount() <= 0 || getValueAt(0, col) == null ? String.class
                : getValueAt(0, col).getClass();
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        ArrayList colArrayList = (ArrayList) data.get(row);
        colArrayList.set(col, obj);
        super.fireTableDataChanged();
    }

    public void insertRow(ArrayList newrow) {
        data.add(newrow);
        super.fireTableDataChanged();
    }

    public void insertRow(ArrayList newrow, int row) {
        data.add(row, newrow);
        super.fireTableDataChanged();
    }

    public void deleteRow(int row) {
        data.remove(row);
        super.fireTableDataChanged();
    }

    public void deleteAfterSelectedRow(int row) {
        int size = this.getRowCount();
        int n = size - (row + 1);
        for (int i = 1; i <= n; i++) {
            data.remove(row + 1);
        }
        super.fireTableDataChanged();
    }

    public ArrayList getRow(int row) {
        return (ArrayList) data.get(row);
    }

    public void updateRow(ArrayList updatedRow, int row) {
        data.set(row, updatedRow);
        super.fireTableDataChanged();
    }

    public void clearTable() {
        data = new ArrayList();
        super.fireTableDataChanged();
    }

}
