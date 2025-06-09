/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author user
 */
public class TableEmployeeModel extends AbstractTableModel {
    List<Employee> lb;
    
    public TableEmployeeModel(List<Employee> lb) {
        this.lb = lb;
    }
    
        @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public int getRowCount() {
        return lb.size();
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Gender";
              default:
                return null;          
        }
    }
    
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return lb.get(row).getId_employee();
            case 1:
                return lb.get(row).getName();
            case 2:
                return lb.get(row).getGender();
            default:
                return null;
        }
    }
}
